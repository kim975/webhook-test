package com.zerobase.report.report.application;

import com.zerobase.report.api.user.UserApi;
import com.zerobase.report.api.user.UserApiDto.UserDetail;
import com.zerobase.report.api.user.UserApiDto.UserListResponse;
import com.zerobase.report.api.user.UserApiDto.UserResponse;
import com.zerobase.report.api.user.UserSearchType;
import com.zerobase.report.report.service.ReportInfo;
import com.zerobase.report.follow.service.FollowInfo;
import com.zerobase.report.follow.service.FollowService;
import com.zerobase.report.report.service.ReportInfo.Main;
import com.zerobase.report.report.service.ReportService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportFacade {

    private final ReportService reportService;
    private final FollowService followService;
    private final UserApi userApi;

    public ReportFacadeDto.GetReportResponse getMyReport(String userUuid, Long reportSeq) {
        UserResponse user = userApi.getUser(userUuid, UserSearchType.USER_UUID);
        Main myReport = reportService.getMyReport(user.getData().getId(), reportSeq);

        return ReportFacadeDto.GetReportResponse.from(myReport, user.getData());
    }

    public Page<ReportFacadeDto.GetReportResponse> getMyReports(String userUuid, Pageable pageable) {
        UserResponse user = userApi.getUser(userUuid, UserSearchType.USER_UUID);
        Page<Main> myReports = reportService.getMyReports(user.getData().getId(), pageable);

        return myReports.map(report -> ReportFacadeDto.GetReportResponse.from(report, user.getData()));
    }

    public ReportFacadeDto.GetReportResponse getReport(String nickname, Long reportSeq) {
        UserResponse user = userApi.getUser(nickname, UserSearchType.NICKNAME);
        Main report = reportService.getReport(user.getData().getId(), reportSeq);

        return ReportFacadeDto.GetReportResponse.from(report, user.getData());
    }

    public Page<ReportFacadeDto.GetReportResponse> getReports(List<String> nickname, Pageable pageable) {
        UserListResponse usersResponse = userApi.getUsers(nickname, UserSearchType.NICKNAME);

        Page<Main> reports = reportService.getReports(
            usersResponse.getData().stream()
                .map(UserDetail::getId)
                .toList()
            , pageable
        );

        Set<Long> pageIdSet = reports.stream().map(Main::getUserId).collect(Collectors.toSet());

        List<UserDetail> filterUserList = usersResponse.getData().stream()
            .filter(userDetail -> pageIdSet.stream().anyMatch(id -> id.equals(userDetail.getId()))).toList();

        return reports.map(report -> ReportFacadeDto.GetReportResponse.from(report,
                filterUserList
                    .stream()
                    .filter(
                        user -> report.getUserId().equals(user.getId())
                    )
                    .findFirst()
                    .get()
            )
        );
    }

    public ReportInfo.Main modifyReport(ReportFacadeDto.ModifyReportRequest dto) {
        UserResponse user = userApi.getUser(dto.getUserUuid(), UserSearchType.USER_UUID);
        return reportService.modifyReport(dto.toCommand(user.getData().getId()));
    }

    public void deleteReport(String userUuid, Long reportSeq) {

        UserResponse user = userApi.getUser(userUuid, UserSearchType.USER_UUID);

        reportService.deleteReport(user.getData().getId(), reportSeq);
    }

    public Page<ReportFacadeDto.GetReportResponse> getFollowMainReport(String userUuid, Pageable pageable) {
        UserResponse user = userApi.getUser(userUuid, UserSearchType.USER_UUID);
        List<FollowInfo> followerList = followService.getFollowList(user.getData().getId());

        Page<Main> reports = reportService.getReports(
            followerList.stream()
                .map(FollowInfo::getFollowUserId)
                .toList()
            , pageable
        );

        UserListResponse followUsers = userApi.getUsers(reports.stream().map(Main::getUserId).toList());

        return reports.map(report -> ReportFacadeDto.GetReportResponse.from(report,
            followUsers.getData()
                    .stream()
                    .filter(
                        followUser -> report.getUserId().equals(followUser.getId())
                    )
                    .findFirst()
                    .get()
            )
        );
    }
}
