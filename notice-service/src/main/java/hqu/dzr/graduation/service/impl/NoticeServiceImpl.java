package hqu.dzr.graduation.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hqu.dzr.graduation.mapper.NoticeDao;
import hqu.dzr.graduation.mapper.NoticedDao;
import hqu.dzr.graduation.mapper.UserDao;
import hqu.dzr.graduation.model.Notice;
import hqu.dzr.graduation.model.NoticeDetailed;
import hqu.dzr.graduation.model.Noticed;
import hqu.dzr.graduation.model.User;
import hqu.dzr.graduation.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private NoticedDao noticedDao;

    @Autowired
    private UserDao userDao;

    /**
     * 查询所有的公告
     * @return
     */
    @Override
    public List<Notice> searchAllNotice() {
        return noticeDao.selectList(null);
    }

    /**
     * 查询用户公告阅读记录
     * @param phoneNumber
     * @return
     */
    @Override
    public List<NoticeDetailed> searchNoticedByPhoneNumber(String phoneNumber) {
        List<NoticeDetailed> res = new ArrayList<>();
        List<Noticed> noticeds = noticedDao.selectList(new QueryWrapper<Noticed>().eq("phone_number", phoneNumber));
        for (Noticed noticed : noticeds) {
            Notice notice = noticeDao.selectById(noticed.getNoticeId());
            if (notice == null) continue;
            NoticeDetailed detailed = new NoticeDetailed();
            detailed.setNoticeId(notice.getNoticeId());
            detailed.setNoticeTitle(notice.getNoticeTitle());
            detailed.setContext(notice.getContext());
            detailed.setReaded(noticed.getReaded());
            detailed.setDate(notice.getDate());
            detailed.setRemark(notice.getRemark());
            res.add(detailed);
        }
        return res;
    }

    /**
     * 插入公告
     * @param notice
     * @return
     */
    @Override
    public int addNotice(Notice notice) {
        List<User> users = userDao.selectList(null);
        for (User user : users) {
            Noticed noticed = new Noticed();
            noticed.setNoticeId(notice.getNoticeId());
            noticed.setPhoneNumber(String.valueOf(user.getPhoneNumber()));
            noticedDao.insert(noticed);
        }
        return noticeDao.insert(notice);
    }

    /**
     * 通过公告Id删除公告
     * @param noticeId
     * @return
     */
    @Override
    public int deleteNoticeById(Long noticeId) {
        noticeDao.deleteById(noticeId);
        return noticedDao.delete(new QueryWrapper<Noticed>().eq("notice_id", noticeId));
    }

    /**
     * 通过用户电话号码删除阅读记录
     * @param noticeId
     * @param phoneNumber
     * @return
     */
    @Override
    public int deleteNoticedByIdAndPhone(Long noticeId, String phoneNumber) {
        return noticedDao.delete(new QueryWrapper<Noticed>().eq("notice_id", noticeId)
                .eq("phone_number", phoneNumber));
    }

    /**
     * 更改已读记录
     * @param noticeId
     * @param phoneNumber
     * @return
     */
    @Override
    public int updateNoticedStatus(Long noticeId, String phoneNumber) {
        Noticed noticed = noticedDao.selectOne(new QueryWrapper<Noticed>().eq("notice_id", noticeId)
                .eq("phone_number", phoneNumber));
        noticed.setReaded("已读");
        return noticedDao.updateById(noticed);
    }
}
