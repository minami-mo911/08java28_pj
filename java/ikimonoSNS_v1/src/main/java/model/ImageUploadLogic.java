package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.http.Part;

public class ImageUploadLogic {
    private static final String UPLOAD_DIR = "uploads";

    public String uploadImage(Part filePart, String appPath) throws IOException {
        if (filePart == null || filePart.getSize() == 0) {
            return null;  // 画像がアップロードされなかった場合
        }

        // アップロードディレクトリのパス
        String uploadPath = appPath + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        File file = new File(uploadDir, fileName);
        filePart.write(file.getAbsolutePath());  // ファイルを保存

        return UPLOAD_DIR + "/" + fileName;  // 保存したファイルのURLを返す
    }
}