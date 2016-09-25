/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.mail;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.utils.DateUtils;
import java.time.LocalDateTime;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author kieckegard
 */
public class EmprestimoEmail {

    private String  hostName;
    private Integer port;

    public EmprestimoEmail() {
        this.hostName = "smtp.gmail.com";
        this.port = 587;
    }

    private SimpleEmail createSimpleEmail() {

        SimpleEmail simpleEmail = new SimpleEmail();

        simpleEmail.setHostName(hostName);
        simpleEmail.setSmtpPort(port);
        simpleEmail.setStartTLSEnabled(true);
        simpleEmail.setSSLOnConnect(true);

        return simpleEmail;
    }

    public LocalDateTime sendEmail(Emprestimo emprestimo) throws EmailException {

        SimpleEmail simpleEmail = createSimpleEmail();
        simpleEmail.setSubject("SisBiblioteca - Notificação - Empréstimo [ID]: " + emprestimo.getId());
        simpleEmail.setMsg("Olá, " + emprestimo.getAluno().getNome() + ", \n"
                + "Falta apenas 1 dia para finalização do Empréstimo do livro " + emprestimo.getLivro().getTitulo() + "!\n"
                + "Por favor, proceder com a devolução do livro até a data " + DateUtils.formatToBrazilPattern(emprestimo.getEndDate()));
        simpleEmail.setAuthentication("sysagenda@gmail.com", "Sisagendapoo");
        simpleEmail.setFrom("sysagenda@gmail.com", "sisBiblioteca");
        simpleEmail.addTo(emprestimo.getAluno().getEmail());
        simpleEmail.send();
        
        return LocalDateTime.now();
    }
}
