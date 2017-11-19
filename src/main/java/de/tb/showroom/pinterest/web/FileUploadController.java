package de.tb.showroom.pinterest.web;

import de.tb.showroom.pinterest.model.Pin;
import de.tb.showroom.pinterest.repositories.PinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@Controller
public class FileUploadController {

    @LocalServerPort
    private String port;

    @Value("${file.upload.dir}")
    private String fileUploadDir;

    @Value("${file.server.basepath}")
    private String fileServerBasePath;

    @Autowired
    private PinRepository pinRepository;

    @GetMapping(value = "${file.server.basepath}/{resourceId}")
    public void serve(HttpServletResponse response, @PathVariable("resourceId")String resourceId) {
        String targetFilename = new String(Base64Utils.decodeFromUrlSafeString(resourceId));
        try (OutputStream os = response.getOutputStream(); FileInputStream fis = new FileInputStream(targetFilename)) {
            StreamUtils.copy(fis, os);
            response.setStatus(200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "${file.upload.basepath}")
    public ResponseEntity<Pin> process(MultipartHttpServletRequest request) {
        Map<String, MultipartFile> map = request.getFileMap();
        MultipartFile multipartFile = request.getFile("file");
        File localFile = new File(this.fileUploadDir, multipartFile.getOriginalFilename());
        saveMultipartFile(multipartFile, localFile);
        String url = getImageUrl(localFile);
        Pin savedPin = pinRepository.save(new Pin(url));
        return ResponseEntity.ok(savedPin);
    }

    private String getImageUrl(File localFile) {
        String baseUrl = "http://localhost:" + this.port + this.fileServerBasePath + "/";
        String encodedFilename = Base64Utils.encodeToUrlSafeString(localFile.getAbsolutePath().getBytes());
        return baseUrl + encodedFilename;
    }

    public void saveMultipartFile(final InputStreamSource iss, File file) {
        try (InputStream is = iss.getInputStream(); OutputStream fos = new FileOutputStream(file)) {
            StreamUtils.copy(is, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
