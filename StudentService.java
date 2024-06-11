package com.example.service;

import com.example.dao.StudentDao;
import com.example.domain.PageBean;
import com.example.domain.Student;

import java.util.List;

public class StudentService {
    private StudentDao studentDao = new StudentDao();
    public List<Student> findAll() {
        return studentDao.findAll();
    }
    public int addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    public int deleteById(int id) {
        return studentDao.deleteById(id);
    }

    public PageBean<Student> findStudentByPage(String current, String r) {
        int currentPage = Integer.parseInt(current);
        int rows = Integer.parseInt(r);
        if (currentPage <= 0) {
            currentPage = 1;
        }
        PageBean<Student> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        StudentDao studentDao = new StudentDao();
        int totalCount = studentDao.findTotalCount();
        pageBean.setTotalCount(totalCount);
        int totalPage = totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1;
        pageBean.setTotalPage(totalPage);
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        pageBean.setCurrentPage(currentPage);
        int start = (currentPage - 1) * rows;
        List<Student> list = studentDao.findByPage(start,rows);
        pageBean.setList(list);
        return pageBean;
    }
}
