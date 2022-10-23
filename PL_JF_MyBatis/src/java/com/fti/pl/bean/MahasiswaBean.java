/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fti.pl.bean;

import com.fti.pl.models.Mahasiswa;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author faffn
 */

@Named("userAct")
@SessionScoped

public class MahasiswaBean implements Serializable {

    public MahasiswaBean() {
    }
    
    public void doCreateMahasiswa() throws IOException {
        Reader reader = Resources.getResourceAsReader("com/fti/pl/config/ConfigMyBatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Mahasiswa mhs = new Mahasiswa("672020113", "Fandi");
        sqlSession.insert("Mahasiswa.insert", mhs);
        sqlSession.commit();
        sqlSession.close();
    }
    
    public void doUpdateMahasiswa() throws IOException {
        Reader reader = Resources.getResourceAsReader("com/fti/pl/config/ConfigMyBatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        
        Mahasiswa mhs = (Mahasiswa) session.selectOne("Mahasiswa.getById", "672020113");
        System.out.println("Current details of the student are");
        System.out.println(mhs.toString());
        
        mhs.setNama("Bayu");
        
        session.update("Mahasiswa.update", mhs);
        System.out.println("Update Successfully");
        session.commit();
        session.close();
        
//        Mahasiswa mh = (Mahasiswa) session.selectOne("Mahasiswa.getById", "672020113");
//        System.out.println("Details of student after update");
//        System.out.println(mh.toString());
//        session.commit();
//        session.close();
    }
    
    public void doDeleteMahasiswa() throws IOException {
        Reader reader = Resources.getResourceAsReader("com/fti/pl/config/ConfigMyBatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        
        session.delete("Mahasiswa.deleteById", "672020113");
        session.commit();
        session.close();
        System.out.println("Delete Successfully");
    }
}
