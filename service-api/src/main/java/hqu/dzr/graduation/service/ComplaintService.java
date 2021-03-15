package hqu.dzr.graduation.service;

import hqu.dzr.graduation.model.Complaint;
import hqu.dzr.graduation.model.Repair;

import java.util.List;

public interface ComplaintService {

    int addComplaint(Complaint complaint);

    int updateComplaint(Complaint complaint);

    List<Complaint> searchAllComplaint();

    List<Complaint> searchByName(String name);

    int updateComplaintStatusById(Long id);

    List<Complaint> searchComplaintsById(Long id);

    List<Complaint> searchComplaintByIdAndText(String text, Long id);

    int deleteComplaintById(Long id);
}
