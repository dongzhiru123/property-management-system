package hqu.dzr.graduation.service;

import hqu.dzr.graduation.model.Repair;

import java.util.List;

public interface RepairService {

    int addRepair(Repair repair);

    int updateRepair(Repair repair);

    List<Repair> searchAllRepair();

    List<Repair> searchByName(String name);

    int updateRepairStatusById(Long id);

    int deleteRepairById(Long id);

    List<Repair> searchRepairsById(Long id);

    List<Repair> searchRepairsByIdAndText(String text, Long id);
}
