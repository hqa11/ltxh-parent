package com.agc.worker.dao.platnews;

import com.agc.core.common.Page;
import com.agc.core.common.Pageable;
import com.agc.dubbo.model.Plat_Msg;
import com.agc.dubbo.model.Plat_Msg_Attachment;
import com.agc.worker.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PlatNewsDao extends BaseDao<Plat_Msg, Integer> {

    public Page<Plat_Msg> getPlatMsgPage(Pageable pb) {
        String sql = "SELECT f.*,u.u_nick_name username FROM plat_msg f LEFT JOIN user u ON u.u_id=f.pm_sender_userid WHERE f.pm_is_del = 0";
        Map<String, Object> o = pb.getQmp();
        if (o != null) {
            if (o.get("start") != null) {
                sql += " AND f.pm_send_time >= ?";
                pb.addQueryVal(o.get("start"));
            }
            if (o.get("end") != null) {
                sql += " AND f.pm_send_time <= ?";
                pb.addQueryVal(o.get("end"));
            }
        }
        sql += " order by f.pm_send_time desc ";
        Page<Plat_Msg> page = getPage(sql, pb, Plat_Msg.class);
        return page;
    }

    public List<Plat_Msg_Attachment> getAttachmentsByMsgId(Integer pm_id) {
        String sql = "SELECT * FROM plat_msg_attachment WHERE  pma_is_del = 0 AND pma_pm_id = ?";
        List<Plat_Msg_Attachment> attachments = getList(sql, Plat_Msg_Attachment.class, pm_id);
        return attachments;
    }

    public void deleteOldAttachments(Object pk) {
        String sql = "UPDATE plat_msg_attachment SET pma_is_del = 1 WHERE pma_pm_id = ?";
        jtp.update(sql, pk);
    }

}
