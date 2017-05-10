package ru.javastudy.springMVC.controller;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.javastudy.springMVC.model.Document;
import ru.javastudy.springMVC.model.User;
import ru.javastudy.springMVC.service.DocService;
import ru.javastudy.springMVC.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;

@SessionAttributes("userJSP")
@Controller
public class MainController {
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file, @ModelAttribute("userJSP") User user, HttpServletRequest request) throws Exception {
        Integer parentId = new Integer(request.getParameter("parentId"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("directory");
        DocService ds = new DocService();
        modelAndView.addObject("docId", parentId);
        modelAndView.addObject("ds", ds);
        Document curDir = ds.get(parentId);
        String name;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                name = file.getOriginalFilename();

                String rootPath = "C:\\Users\\user\\Documents\\Веб-приложение\\";
                File dir = new File(rootPath);

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String uploadPath = "";
                if (parentId == 0) {
                    //System.out.println(dir.getAbsolutePath() + File.separator + name);
                    uploadPath = dir.getAbsolutePath() + File.separator;
                } else {
                    //System.out.println(dir.getAbsolutePath() + File.separator + curDir.getDocPath() + curDir.getName() + File.separator + name);
                    uploadPath = dir.getAbsolutePath() + File.separator + curDir.getDocPath() + curDir.getName() + File.separator;
                    File dir2 = new File(uploadPath);
                    if (!dir2.exists()) {
                        dir2.mkdirs();
                    }
                }
                //File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);
                File uploadedFile = new File(uploadPath + name);
                if (uploadedFile.exists()) {
                    request.setAttribute("message", "Файл с таким именем уже существует!");
                    return modelAndView;
                }

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                String pathhh = "";
                if (parentId != 0) pathhh = curDir.getDocPath() + curDir.getName() + File.separator;

                Document uploadDocument = new Document(0, name, user.getLogin(), pathhh, parentId, false, false);
                //System.out.println(uploadDocument);
                ds.createDocument(uploadDocument);

                request.setAttribute("message", "Файл успешно загружен!");
                return modelAndView;

            } catch (Exception e) {
                request.setAttribute("message", "Ошибка загрузки!");
                return modelAndView;
            }
        } else {
            request.setAttribute("message", "Ошибка загрузки!");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/createFolder")
    public ModelAndView createFolder(@RequestParam("nameFile") String folderName, @ModelAttribute("userJSP") User user, HttpServletRequest request) throws Exception {
        Integer parentId = new Integer(request.getParameter("parentId"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("directory");
        DocService ds = new DocService();
        modelAndView.addObject("docId", parentId);
        modelAndView.addObject("ds", ds);
        Document curDir = ds.get(parentId);
        if (folderName.isEmpty()) return modelAndView;

        try {
            String rootPath = "C:\\Users\\user\\Documents\\Веб-приложение\\";
            File dir = new File(rootPath);

            String uploadPath = "";
            if (parentId == 0) {
                uploadPath = dir.getAbsolutePath() + File.separator;
            } else {
                uploadPath = dir.getAbsolutePath() + File.separator + curDir.getDocPath() + curDir.getName() + File.separator;
            }
            File uploadedFile = new File(uploadPath + folderName + File.separator);

            if (uploadedFile.exists()) {
                request.setAttribute("message", "Директория с таким именем уже существует!");
                return modelAndView;
            }
            uploadedFile.mkdirs();

            String newPath = "";
            if (parentId != 0) {
                Document parDoc = ds.get(parentId);
                newPath = parDoc.getDocPath() + parDoc.getName() + File.separator;
            }
            Document uploadDocument = new Document(0, folderName, user.getLogin(), newPath, parentId, true, false);
            ds.createDocument(uploadDocument);
        } catch (Exception e) {
            return modelAndView;
        }
        return modelAndView;
    }

    public void deleteFromBD(DocService ds, Integer id) throws Exception {
        ArrayList<Document> children = (ArrayList<Document>) ds.getDirectory(id);
        for (Document d : children) {
            deleteFromBD(ds, d.getDocId());
        }
        ds.deleteDocument(id);
    }

    public void deleteFromReality(File file) {
        if (!file.exists())
            return;
        if (file.isDirectory()) {
            for (File f : file.listFiles())
                deleteFromReality(f);
        }
        file.delete();
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteFileOrFolder(@RequestParam("marked") Integer markedFile, @ModelAttribute("userJSP") User user, HttpServletRequest request) throws Exception {
        Integer parentId = new Integer(request.getParameter("parentId"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("directory");
        DocService ds = new DocService();
        modelAndView.addObject("docId", parentId);
        modelAndView.addObject("ds", ds);
        String rootPath = "C:\\Users\\user\\Documents\\Веб-приложение\\";
        File dir = new File(rootPath);
        Document curDir = ds.get(markedFile);

        if (curDir != null) {
            if (!curDir.getOwner().equals(user.getLogin())) {
                request.setAttribute("message", "Эту операцию может выполнить только владелец!");
                return modelAndView;
            }
            String deletedPath = dir.getAbsolutePath() + File.separator + curDir.getDocPath() + curDir.getName();
            deleteFromReality(new File(deletedPath));
            deleteFromBD(ds, markedFile);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/back")
    public ModelAndView back(@ModelAttribute("userJSP") User user, HttpServletRequest request) throws Exception {
        Integer parentId = new Integer(request.getParameter("parentId"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("directory");
        DocService ds = new DocService();
        Document curDir = ds.get(parentId);
        modelAndView.addObject("docId", curDir.getParentId());
        modelAndView.addObject("ds", ds);

        return modelAndView;
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public ModelAndView doDownload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("userJSP") User user) throws Exception {
        Integer myId = new Integer(request.getParameter("myId"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("directory");
        DocService ds = new DocService();
        modelAndView.addObject("docId", myId);
        modelAndView.addObject("ds", ds);

        final int BUFFER_SIZE = 4096;
        String filePath = "C:\\Users\\user\\Documents\\Веб-приложение";

        Document curDir = ds.get(myId);

        if (!curDir.getOwner().equals(user.getLogin())) {
            request.setAttribute("message", "Эту операцию может выполнить только владелец!");
            return modelAndView;
        }

        String fullPath = filePath + File.separator + curDir.getDocPath() + curDir.getName();

        ServletContext context = request.getServletContext();

        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        response.setHeader("Content-Disposition", "attachment; filename=\"" + MimeUtility.encodeWord(downloadFile.getName(), "utf-8", "Q") + "\"");

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();
        return modelAndView;
    }

    /*Попадаем сюда на старте приложения*/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userJSP", new User());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/check-user")
    public ModelAndView checkUser(@ModelAttribute("userJSP") User user, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        UserService us = new UserService();
        if (us.auth(user.getLogin(), user.getPassword())) {
            modelAndView.setViewName("directory");
            DocService ds = new DocService();
            int root = 0;
            modelAndView.addObject("docId", root);
            modelAndView.addObject("ds", ds);
        } else {
            request.setAttribute("message", "Не удается войти.\nПожалуйста, проверьте правильность написания логина и пароля.");
            modelAndView.setViewName("index");
        }

        modelAndView.addObject("userJSP", user);
        return modelAndView;
    }

    @RequestMapping(value = "/directory/{directory}")
    public ModelAndView showFiles(@ModelAttribute("userJSP") User user, @PathVariable String directory, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("directory");

        DocService ds = new DocService();
        modelAndView.addObject("ds", ds);
        HttpSession session = request.getSession();

        /*Integer newId = (Integer) session.getAttribute("newId");
        modelAndView.addObject("docId", newId);*/
        modelAndView.addObject("docId", directory);
        modelAndView.addObject("userJSP", user);
        return modelAndView;
    }

    @RequestMapping(value = "/reg-user")
    public ModelAndView regUser(@ModelAttribute("userJSP") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("reg");
        modelAndView.addObject("userJSP", user);
        return modelAndView;
    }

    @RequestMapping(value = "/checkLogin")
    public ModelAndView checkLogin(@ModelAttribute("userJSP") User user, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        UserService us = new UserService();
        if (us.register(user.getLogin(), user.getPassword())) {
            modelAndView.setViewName("RegSuccess");
        } else {
            request.setAttribute("message", "Введенный логин уже занят!");
            modelAndView.setViewName("reg");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/deleteUser")
    public ModelAndView deleteUser(@ModelAttribute("userJSP") User user) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        UserService us = new UserService();
        DocService ds = new DocService();

        ArrayList<Document> userDocs = us.getUserDocuments(user.getLogin());
        String rootPath = "C:\\Users\\user\\Documents\\Веб-приложение\\";
        File dir = new File(rootPath);

        for (Document d : userDocs) {
            if (d != null) {
                String deletedPath = dir.getAbsolutePath() + File.separator + d.getDocPath() + d.getName();
                deleteFromReality(new File(deletedPath));
                deleteFromBD(ds, d.getDocId());
            }
        }



        us.deleteUser(user.getLogin());
        modelAndView.setViewName("index");
        return modelAndView;
    }
}