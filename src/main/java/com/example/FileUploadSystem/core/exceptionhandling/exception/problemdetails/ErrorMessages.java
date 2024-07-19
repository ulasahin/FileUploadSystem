package com.example.FileUploadSystem.core.exceptionhandling.exception.problemdetails;

public final class ErrorMessages {
    private ErrorMessages() {
        throw new UnsupportedOperationException("Bu sabit bir sınıftır ve örneklenemez.");
    }

    public static final String EMAİL_USED = "Bu email daha önce kullanılmıştır.";
    public static final String INVALID_PASS_EMAİL = "E-posta ya da şifre yanlıştır.";
    public static final String NOT_SAME_PASS = "Şifreler aynı değil.";
    public static final String FILE_CANNOT_SAVE = "Dosya depolanamadı. Hata: ";
    public static final String FILE_CANNOT_DELETE = "Dosya fiziksel olarak silinemedi. Hata: ";
    public static final String EMAİL_NOT_FOUND = "Böyle bir e-mail bulunamamıştır.";
    public static final String FILE_NOT_FOUND = "Böyle bir dosya bulunamadı.";
    public static final String USER_NOT_FOUND_FOR_UPDATE = "Güncellenmek istenen kullanıcı bulunamadı!";
    public static final String USER_NOT_FOUND = "Böyle bir kullanıcı bulunamadı.";
}
