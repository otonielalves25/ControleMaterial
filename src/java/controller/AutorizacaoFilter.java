/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author otoniel.aalves
 */
public abstract class AutorizacaoFilter implements Filter {

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String url = req.getRequestURI();

        HttpSession sessao = req.getSession();

        try {
            if (sessao.getAttribute("usuarioLogado") != null
                    || url.equalsIgnoreCase("/ControleMaterial/index.jsp")
                    || url.equalsIgnoreCase("/ControleMaterial/LoginServlet")
                    || url.endsWith(".png")
                    || url.endsWith(".css")
                    
                    ) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect("index.jsp");
            }

        } catch (IOException | ServletException erro) {
            request.setAttribute("erro", erro);
            request.getRequestDispatcher("/erro.jsp").forward(request, response);

        }

    }


}
