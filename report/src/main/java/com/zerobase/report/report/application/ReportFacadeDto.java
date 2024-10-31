package com.zerobase.report.report.application;

import com.zerobase.report.api.user.UserApiDto;
import com.zerobase.report.report.service.BookInfo;
import com.zerobase.report.report.service.ReportCommand;
import com.zerobase.report.report.service.ReportInfo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ReportFacadeDto {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class GetReportResponse {

        private Long id;
        private BookDetail book;
        private UserDetail user;
        private Long bookReportSeq;
        private LocalDateTime readDateTime;
        private String text;
        private Boolean reveal;

        public static GetReportResponse from(ReportInfo.Main report, UserApiDto.UserDetail user) {
            return GetReportResponse.builder()
                .id(report.getId())
                .book(BookDetail.from(report.getBook()))
                .user(UserDetail.from(user))
                .bookReportSeq(report.getBookReportSeq())
                .readDateTime(report.getReadDateTime())
                .text(report.getText())
                .reveal(report.getReveal())
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class UserDetail {

        private Long id;
        private String loginId;
        private String name;
        private String nickname;
        private String email;
        private String phoneNumber;
        private String userUuid;

        public static UserDetail from(UserApiDto.UserDetail user) {
            return UserDetail.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .userUuid(user.getUserUuid())
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class BookDetail {

        private Long id;
        private String title;
        private String author;
        private String publisher;
        private String isbn;
        private String thumbnailImageUrl;
        private LocalDate publishedDate;

        public static BookDetail from(BookInfo.Main book) {
            return BookDetail.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .thumbnailImageUrl(book.getThumbnailImageUrl())
                .publishedDate(book.getPublishedDate())
                .build();
        }

    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class ModifyReportRequest {
        private Long bookId;
        private Long reportSeq;
        private String userUuid;
        private LocalDateTime readDatetime;
        private String text;
        private Boolean reveal;

        public ReportCommand.ModifyReport toCommand(Long userId) {
            return ReportCommand.ModifyReport.builder()
                .reportSeq(reportSeq)
                .userId(userId)
                .bookId(bookId)
                .reveal(reveal)
                .readDatetime(readDatetime)
                .text(text)
                .build();
        }

    }
}
