/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProdutoDao;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Produto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

;

/**
 *
 * @author otoniel.aalves
 */
public class ImpressaoServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String caminhoJasper = getServletContext().getRealPath("/impressao/material.jasper");

        List<Produto> lista = new ProdutoDao().buscaTudo(Produto.class);

        JRBeanCollectionDataSource equipamentos = new JRBeanCollectionDataSource(lista, false);  // DEIXAR COM FALSE, SERÃO DÁ ERRO NO REPORT, 

        // Cria os dados para gerar
        JasperPrint jasperPrint = null;
        byte[] bytes = null;   

        try {
            jasperPrint = JasperFillManager.fillReport(caminhoJasper, new HashMap(), equipamentos);
            //jasperPrint = JasperFillManager.fillReport(jasperURL.openStream(), new HashMap(), equipamentos);
        } catch (JRException ex) {
            Logger.getLogger(ImpressaoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            
            bytes = JasperExportManager.exportReportToPdf(jasperPrint);   
            
        } catch (JRException ex) {
            Logger.getLogger(ImpressaoServlet.class.getName()).log(Level.INFO, null, ex);
        }

        if (bytes != null) {

            response.setHeader("Content-Disposition", "attachment;filename=arquivo.pdf");
            response.getOutputStream().write(bytes);

//            // Define os dados do arquivo.
//            response.setContentType("application/pdf;charset=UTF-8");
//            response.setContentLength(bytes.length);
//            response.setHeader("Content-Disposition", "attachment;filename=impresa.pdf");
//
//            // Responsável por mostar na tela e abrir o arquivo.
//            try (ServletOutputStream outputstream = response.getOutputStream()) {
//                outputstream.write(bytes, 0, bytes.length);
//                outputstream.flush();
//            }
        }

    }

}
