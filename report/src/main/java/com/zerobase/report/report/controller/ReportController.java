package com.zerobase.report.report.controller;

import com.zerobase.report.common.response.CommonResponse;
import com.zerobase.report.report.application.BookFacade;
import com.zerobase.report.report.application.ReportFacade;
import com.zerobase.report.report.controller.ReportDto.GetReportResponse;
import com.zerobase.report.report.controller.ReportDto.ModifyReportRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final BookFacade bookFacade;
    private final ReportFacade reportFacade;

    @PostMapping("/report")
    @PreAuthorize("hasRole('USER')")
    public CommonResponse<ReportDto.CreateReportResponse> createReport(
        Authentication authentication,
        @RequestBody ReportDto.CreateReportRequest request
    ) {
        return CommonResponse.success(
            ReportDto.CreateReportResponse.from(
                bookFacade.createReport(request.toFacadeDto(authentication.getName()))
            )
        );
    }

    @GetMapping("/my-report/{reportSeq}")
    @PreAuthorize("hasRole('USER')")
    public CommonResponse<GetReportResponse> getMyReport(
        Authentication authentication,
        @PathVariable long reportSeq
    ) {

        return CommonResponse.success(
            GetReportResponse.from(reportFacade.getMyReport(authentication.getName(), reportSeq))
        );
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/my-reports")
    public CommonResponse<Page<GetReportResponse>> getMyReports(
        Authentication authentication,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "0") int page
    ) {
        return CommonResponse.success(
            reportFacade.getMyReports(authentication.getName(), PageRequest.of(page, size))
                .map(GetReportResponse::from)
        );
    }
    @GetMapping("/report/{nickname}/{reportSeq}")
    @PreAuthorize("hasRole('USER')")
    public CommonResponse<GetReportResponse> getReport(
        @PathVariable String nickname,
        @PathVariable long reportSeq
    ) {

        return CommonResponse.success(
            GetReportResponse.from(reportFacade.getReport(nickname, reportSeq))
        );
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/reports")
    public CommonResponse<Page<GetReportResponse>> getMyReport(
        @RequestParam List<String> nicknames,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "0") int page
    ) {
        return CommonResponse.success(
            reportFacade.getReports(nicknames, PageRequest.of(page, size))
                .map(GetReportResponse::from)
        );
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/report/{reportSeq}")
    public CommonResponse<ReportDto.ModifyReportResponse> modifyReport(
        Authentication authentication,
        @PathVariable Long reportSeq,
        @RequestBody ModifyReportRequest request
    ) {
        return CommonResponse.success(
            ReportDto.ModifyReportResponse.from(
                reportFacade.modifyReport(request.toDto(authentication.getName(), reportSeq))
            )
        );
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/report/{reportSeq}")
    public CommonResponse<Void> modifyReport(
        Authentication authentication,
        @PathVariable Long reportSeq
    ) {
        reportFacade.deleteReport(authentication.getName(), reportSeq);
        return CommonResponse.success();
    }
  
    @GetMapping("/main")
    public CommonResponse<Page<GetReportResponse>> getFollowReport(
        Authentication authentication,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "0") int page
    ) {
        return CommonResponse.success(
            reportFacade.getFollowMainReport(authentication.getName(), PageRequest.of(page, size))
                .map(GetReportResponse::from)
        );
    }
}
