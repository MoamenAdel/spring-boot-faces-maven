/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.JSFBackingBeans;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Scope;

import com.research.entity.ProjectTypes;
import com.research.entity.ProjectEmployees;
import java.util.ArrayList;
import java.util.Collection;
import com.research.entity.Lfm;
import com.research.JSFBackingBeans.exceptions.NonexistentEntityException;
import com.research.entity.Docs;
import com.research.entity.Project;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Moamenovic
 */

@Scope(value = "session")
@Component(value = "ProjectJpaController")
@ELBeanName(value = "ProjectJpaController")
@Join(path = "/project", to = "/project/Create.xhtml")
public class ProjectJpaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9006980830134897009L;
	
}
