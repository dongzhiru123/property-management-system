package hqu.dzr.graduation.service;

import hqu.dzr.graduation.model.Notice;
import hqu.dzr.graduation.model.NoticeDetailed;
import hqu.dzr.graduation.model.Noticed;

import java.util.List;

public interface NoticeService {

    List<Notice> searchAllNotice();

    List<NoticeDetailed> searchNoticedByPhoneNumber(String phoneNumber);

    int addNotice(Notice notice);

    int deleteNoticeById(Long noticeId);

    int deleteNoticedByIdAndPhone(Long noticeId, String phoneNumber);

    int updateNoticedStatus(Long noticeId, String phoneNumber);
}
