package com.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.model.Medida;

public class ContadorDAO {

	private SqlSessionFactory sqlSessionFactory = null;
	 
    public ContadorDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    
    @SuppressWarnings("unchecked")
    public  List<Medida> selectAll(){
        List<Medida> list = null;
        SqlSession session = sqlSessionFactory.openSession();
 
        try {
            list = session.selectList("Medida.selectAll");
        } finally {
            session.close();
        }
        System.out.println("selectAll() --> "+list);
        return list;
 
    }
    
}
