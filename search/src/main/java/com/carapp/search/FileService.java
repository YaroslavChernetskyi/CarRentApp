package com.carapp.search;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;

@Service
public class FileService {

    //@Value("${app.upload.dir:${user.home}}")
   // public String uploadDir = "/home/yarykssd/pictures_dlia_labky";
   /* private String saveFile(MultipartFile file) throws IOException, IOException {
        final String imagePath = "src/main/images/"; //path
        FileOutputStream output = new FileOutputStream(imagePath+file.getOriginalFilename());
        output.write(file.getBytes());

        return imagePath+file.getOriginalFilename();
    }*/
    public void uploadFile(MultipartFile file) {

        try {
            final String imagePath = "src/main/images/cars"; //path
            FileOutputStream output = new FileOutputStream(imagePath+file.getOriginalFilename());
            output.write(file.getBytes());
           /* Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);*/
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }
}