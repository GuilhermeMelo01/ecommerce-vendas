package com.guilhermemelo.course.services;

import com.guilhermemelo.course.services.exception.FileException;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.entity.BufferedHttpEntity;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

    public BufferedImage getJpgImageFromFile(MultipartFile uploadFile) {
        String ext = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
        if (!"png".equals(ext) && !"jpg".equals(ext)) {
            throw new FileException("Só imagens PNG e JPG são permitidas!");
        }
        try {
            BufferedImage img = ImageIO.read(uploadFile.getInputStream());
            if ("png".equals(ext)) {
                img = pngToJpg(img);
            }
            return img;
        } catch (IOException e) {
            throw new FileException("não foi possivel ler o arquivo");
        }
    }

    public BufferedImage pngToJpg(BufferedImage img) {
        BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
        return jpgImage;
    }

    public InputStream getInputStream(BufferedImage img, String extension) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, extension, os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            throw new FileException("Erro ao ler o arquivo");
        }
    }

    public BufferedImage cropSquare(BufferedImage sourceImg){
        int min = Math.min(sourceImg.getHeight(), sourceImg.getWidth());
        return Scalr.crop(
                sourceImg,
                (sourceImg.getWidth()/2) - (min/2),
                (sourceImg.getHeight()/2) - (min/2),
                min,
                min);
    }

    public BufferedImage resize(BufferedImage sourceImg, int size){
        return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
    }

}
