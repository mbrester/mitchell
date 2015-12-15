/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mjb.mitchellsmagicsupliesadminservice.controller;


import edu.wctc.mjb.mitchellsmagicsupliesadminservice.entity.MagicSuply;
import edu.wctc.mjb.mitchellsmagicsupliesadminservice.entity.Manufacture;
import edu.wctc.mjb.mitchellsmagicsupliesadminservice.service.MagicSuplyFacade;
import edu.wctc.mjb.mitchellsmagicsupliesadminservice.service.ManufactureFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Brester
 */
@WebServlet(name = "ManController", urlPatterns = {"/ManController"})
public class ManufactureController extends HttpServlet {

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
    private final static String ACTION_NEW = "new";
    private final static String ACTION_DELETE = "delete";
    private final static String PARAM_ACTION = "action";
    private final static String LIST_PAGE = "/list_manufacture.jsp";
    private final static String ADD_PAGE = "/add_manufacture.jsp";
    private String destination;
    private final static String EDIT_PAGE = "/edit_manufacture.jsp";
    private Manufacture manufacture;
    
     @Inject
     private MagicSuplyFacade magicService;
     @Inject 
     private ManufactureFacade manService;    

      
            
    protected final void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         action = request.getParameter(PARAM_ACTION);
        destination = LIST_PAGE;
               
        try {

                 String manufactureId = request.getParameter("manufactureId");    
         
         
         switch(action){
             case ACTION_LIST:
                 this.getListOfManufacturers(request, manService);
                 destination = LIST_PAGE;
                    break;
             case ACTION_UPDATE:
                 manufacture = manService.find(Integer.parseInt(manufactureId));
                 manufacture.setName(request.getParameter("manufactureName"));
                 manufacture.setCity(request.getParameter("manufactureCity"));
                 manService.edit(manufacture);
                    this.getListOfManufacturers(request, manService);
                 destination = LIST_PAGE;
                         break;
             case ACTION_EDIT:
                 
                 
                  
                          manufacture = manService.find(Integer.parseInt(manufactureId));
                    request.setAttribute("suply", manufacture);
                    destination = EDIT_PAGE;
                    break;
                 case ACTION_ADD:
                    destination = ADD_PAGE;
                    break;
                case "test":
                    manufacture = new Manufacture();
                 manufacture.setName(request.getParameter("manufactureName"));
                 manufacture.setCity(request.getParameter("manufactureCity"));
                    manService.create(manufacture);
                    this.getListOfManufacturers(request, manService);
                    destination = LIST_PAGE;
                     break;
                    
                case ACTION_DELETE:
                    manufacture = manService.find(Integer.parseInt(manufactureId));
                    manService.remove(manufacture);
                    this.getListOfManufacturers(request, manService);
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

    private  void getListOfManufacturers(HttpServletRequest request, ManufactureFacade manService) throws Exception {
        List<Manufacture> manufactures = manService.findAll();
        request.setAttribute("manufactures", manufactures);
        
    }
    @Override
    public final void init() throws ServletException {
        
    }

    public final String getAction() {
        return action;
    }

    public final void setAction(String action) {
        if(action == null){
            //needs validation
        }
        this.action = action;
    }

    public final String getPARAM_ACTION() {
        return PARAM_ACTION;
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
        return "MainController{" + "action=" + action + ", dbStrategyClassName=" +", destination=" + destination + '}';
    }

}
