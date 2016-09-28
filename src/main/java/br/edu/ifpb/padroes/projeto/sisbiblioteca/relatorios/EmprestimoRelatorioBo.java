/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.relatorios;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author kieckegard
 */
public class EmprestimoRelatorioBo {

    private final String path;
    private final String pathToReportPackage;

    public EmprestimoRelatorioBo() {
        this.path = this.getClass().getClassLoader().getResource("").getPath();
        this.pathToReportPackage = "br/edu/ifpb/padroes/projeto/sisbiblioteca/jasper/";
    }

    public void imprimir(List<Emprestimo> emprestimos, String jrxmlPath) {

        try {
            JasperReport report = JasperCompileManager.compileReport(jrxmlPath);
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(emprestimos));
            
            JasperExportManager.exportReportToPdfFile(print, "C:/Pedro/emprestimos.pdf");
        }
        catch (JRException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void generatePdfStream(List<Emprestimo> emprestimos, String jrxmlPath, OutputStream outputStream) {
        try {
            JasperReport report = JasperCompileManager.compileReport(jrxmlPath);
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(emprestimos));
            
            JasperExportManager.exportReportToPdfStream(print,outputStream);
        }
        catch (JRException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getPathToReportPackage() {
        return this.pathToReportPackage;
    }

    public String getPath() {
        return this.path;
    }
}
