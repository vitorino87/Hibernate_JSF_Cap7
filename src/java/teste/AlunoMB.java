/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Query;

import org.hibernate.Session;

/**
 *
 * @author tiago.lucas
 */
@ManagedBean
@RequestScoped
public class AlunoMB {

    /**
     * Creates a new instance of AlunoMB
     */
    public AlunoMB() {
    }
    private Aluno aluno;
    private List<Aluno> listaAlunos;
    @PostConstruct
    public void init(){
        this.aluno = new Aluno();
    }
    public String salvar(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(this.aluno);
        session.getTransaction().commit();
        
        session.beginTransaction();
        Query query = session.createQuery("from Aluno");
        this.listaAlunos = query.list();
        
        session.getTransaction().commit();
        
        session.close();
        return "listar";
    }
    public String listar(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Aluno");
        this.listaAlunos = query.list();
        
        session.getTransaction().commit();
        
        session.close();
        return "listar";
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(List<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }
    
}
