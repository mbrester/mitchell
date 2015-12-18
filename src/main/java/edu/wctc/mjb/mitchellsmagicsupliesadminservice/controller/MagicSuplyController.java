/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mjb.mitchellsmagicsupliesadminservice.controller;

import edu.wctc.mjb.mitchellsmagicsupliesadminservice.entity.MagicSuply;
import edu.wctc.mjb.mitchellsmagicsupliesadminservice.entity.Manufacture;
import edu.wctc.mjb.mitchellsmagicsupliesadminservice.service.MagicSuplyService;
import edu.wctc.mjb.mitchellsmagicsupliesadminservice.service.ManufactureService;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Brester
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MagicSuplyController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String action;
    private final static String ACTION_LIST = "list";
    private final static String ACTION_UPDATE = "update";
    private final static String ACTION_EDIT = "edit";
    private final static String ACTION_ADD = "add";
    private final static String ACTION_NEW = "test";
    private final static String ACTION_DELETE = "delete";
    private final static String PARAM_ACTION = "action";
    private String dbStrategyClassName;
    private String daoClassName;
    private String driverClass;
    private String password;
    private String url;
    private String userName;
    private final static String LIST_PAGE = "/list.jsp";
    private final static String ADD_PAGE = "/add.jsp";
    private String destination;
    private final static String EDIT_PAGE = "/edit.jsp";
    private MagicSuply suply;

    protected final void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action = request.getParameter(PARAM_ACTION);
        destination = LIST_PAGE;

        try {
            ServletContext sctx = getServletContext();
            WebApplicationContext ctx
                    = WebApplicationContextUtils.getWebApplicationContext(sctx);
            MagicSuplyService magicService = (MagicSuplyService) ctx.getBean("magicSuplyService");

            ManufactureService manService = (ManufactureService) ctx.getBean("manufactureService");
            if (sctx.getAttribute("color") == null) {
                sctx.setAttribute("color", request.getParameter("color"));
            }

            this.getListOfManufacturers(request, manService);

            switch (action) {
                case ACTION_LIST:
                    this.getListOfMagicSuplies(request, magicService);
                    destination = LIST_PAGE;
                    break;
                case ACTION_UPDATE:
                    suply = magicService.findById(request.getParameter("productId"));
                    suply.setProductDescription(request.getParameter("productDescription"));
                    suply.setProductName(request.getParameter("productName"));
                    suply.setProductPrice(Double.parseDouble(request.getParameter("productPrice")));
                    suply.setProductImageUrl(request.getParameter("productImageUrl"));
                    suply.setManufatureId(manService.findById(request.getParameter("manufactureId")));
                    magicService.edit(suply);
                    this.getListOfMagicSuplies(request, magicService);
                    destination = LIST_PAGE;
                    break;
                case ACTION_EDIT:

                    String magicSuplyId = request.getParameter("productId");
                    MagicSuply mS = null;
                    mS = magicService.findById(magicSuplyId);
                    request.setAttribute("suply", mS);
                    destination = EDIT_PAGE;
                    break;
                case ACTION_ADD:
                    destination = ADD_PAGE;
                    break;
                case ACTION_NEW:
                    suply = new MagicSuply();
                    suply.setProductName(request.getParameter("productName"));
                    suply.setProductDescription(request.getParameter("productDescription"));
                    suply.setProductPrice(Double.parseDouble(request.getParameter("productPrice")));
                    suply.setProductImageUrl(request.getParameter("productImageUrl"));
                    suply.setManufatureId(manService.findById(request.getParameter("manufactureId")));
                    magicService.create(suply);
                    this.getListOfMagicSuplies(request, magicService);
                    destination = LIST_PAGE;
                    break;

                case ACTION_DELETE:
                    suply = magicService.findById(request.getParameter("productId"));
                    magicService.remove(suply);
                    this.getListOfMagicSuplies(request, magicService);
                    destination = LIST_PAGE;
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("err", ex);
        }

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }

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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void getListOfMagicSuplies(HttpServletRequest request, MagicSuplyService magicService) throws Exception {
        List<MagicSuply> suplies = magicService.findAll();
        request.setAttribute("suplies", suplies);

    }

    private void getListOfManufacturers(HttpServletRequest request, ManufactureService manService) throws Exception {
        List<Manufacture> manufactures = manService.findAll();
        request.setAttribute("manufactures", manufactures);

    }

    @Override
    public final void init() throws ServletException {
        driverClass = getServletConfig().getInitParameter("driverClass");
        url = getServletConfig().getInitParameter("url");
        userName = getServletConfig().getInitParameter("userName");
        password = getServletConfig().getInitParameter("password");
        dbStrategyClassName = this.getServletConfig().getInitParameter("dbStrategy");
        daoClassName = this.getServletConfig().getInitParameter("magicDao");
    }

    public final String getAction() {
        return action;
    }

    public final void setAction(String action) {
        if (action == null) {
            //needs validation
        }
        this.action = action;
    }

    public final String getPARAM_ACTION() {
        return PARAM_ACTION;
    }

    public final String getDbStrategyClassName() {
        return dbStrategyClassName;
    }

    public final void setDbStrategyClassName(String dbStrategyClassName) {
        if (dbStrategyClassName == null) {
            //needs validation
        }
        this.dbStrategyClassName = dbStrategyClassName;
    }

    public final String getDaoClassName() {
        return daoClassName;
    }

    public final void setDaoClassName(String daoClassName) {
        if (daoClassName == null) {
            //needs validation
        }
        this.daoClassName = daoClassName;
    }

    public final String getDriverClass() {
        return driverClass;
    }

    public final void setDriverClass(String driverClass) {
        if (driverClass == null) {
            //needs validation
        }
        this.driverClass = driverClass;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        if (password == null) {
            //needs validation
        }
        this.password = password;
    }

    public final String getUrl() {
        return url;
    }

    public final void setUrl(String url) {
        if (url == null) {
            //needs validation
        }
        this.url = url;
    }

    public final String getUserName() {
        return userName;
    }
//needs validation

    public final void setUserName(String userName) {
        this.userName = userName;
    }

    public final String getDestination() {
        return destination;
    }
//needs validation

    public final void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "MainController{" + "action=" + action + ", dbStrategyClassName=" + dbStrategyClassName + ", daoClassName=" + daoClassName + ", driverClass=" + driverClass + ", password=" + password + ", url=" + url + ", userName=" + userName + ", destination=" + destination + '}';
    }

}
