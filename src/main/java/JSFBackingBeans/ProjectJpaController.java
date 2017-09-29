/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFBackingBeans;

import JSFBackingBeans.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.research.entity.ProjectTypes;
import com.research.entity.ProjectEmployees;
import java.util.ArrayList;
import java.util.Collection;
import com.research.entity.Lfm;
import com.research.entity.Docs;
import com.research.entity.Project;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Moamenovic
 */
public class ProjectJpaController implements Serializable {

    public ProjectJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Project project) {
        if (project.getProjectEmployeesCollection() == null) {
            project.setProjectEmployeesCollection(new ArrayList<ProjectEmployees>());
        }
        if (project.getLfmCollection() == null) {
            project.setLfmCollection(new ArrayList<Lfm>());
        }
        if (project.getDocsCollection() == null) {
            project.setDocsCollection(new ArrayList<Docs>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProjectTypes typeId = project.getTypeId();
            if (typeId != null) {
                typeId = em.getReference(typeId.getClass(), typeId.getId());
                project.setTypeId(typeId);
            }
            Collection<ProjectEmployees> attachedProjectEmployeesCollection = new ArrayList<ProjectEmployees>();
            for (ProjectEmployees projectEmployeesCollectionProjectEmployeesToAttach : project.getProjectEmployeesCollection()) {
                projectEmployeesCollectionProjectEmployeesToAttach = em.getReference(projectEmployeesCollectionProjectEmployeesToAttach.getClass(), projectEmployeesCollectionProjectEmployeesToAttach.getId());
                attachedProjectEmployeesCollection.add(projectEmployeesCollectionProjectEmployeesToAttach);
            }
            project.setProjectEmployeesCollection(attachedProjectEmployeesCollection);
            Collection<Lfm> attachedLfmCollection = new ArrayList<Lfm>();
            for (Lfm lfmCollectionLfmToAttach : project.getLfmCollection()) {
                lfmCollectionLfmToAttach = em.getReference(lfmCollectionLfmToAttach.getClass(), lfmCollectionLfmToAttach.getId());
                attachedLfmCollection.add(lfmCollectionLfmToAttach);
            }
            project.setLfmCollection(attachedLfmCollection);
            Collection<Docs> attachedDocsCollection = new ArrayList<Docs>();
            for (Docs docsCollectionDocsToAttach : project.getDocsCollection()) {
                docsCollectionDocsToAttach = em.getReference(docsCollectionDocsToAttach.getClass(), docsCollectionDocsToAttach.getId());
                attachedDocsCollection.add(docsCollectionDocsToAttach);
            }
            project.setDocsCollection(attachedDocsCollection);
            em.persist(project);
            if (typeId != null) {
                typeId.getProjectCollection().add(project);
                typeId = em.merge(typeId);
            }
            for (ProjectEmployees projectEmployeesCollectionProjectEmployees : project.getProjectEmployeesCollection()) {
                Project oldProjectIdOfProjectEmployeesCollectionProjectEmployees = projectEmployeesCollectionProjectEmployees.getProjectId();
                projectEmployeesCollectionProjectEmployees.setProjectId(project);
                projectEmployeesCollectionProjectEmployees = em.merge(projectEmployeesCollectionProjectEmployees);
                if (oldProjectIdOfProjectEmployeesCollectionProjectEmployees != null) {
                    oldProjectIdOfProjectEmployeesCollectionProjectEmployees.getProjectEmployeesCollection().remove(projectEmployeesCollectionProjectEmployees);
                    oldProjectIdOfProjectEmployeesCollectionProjectEmployees = em.merge(oldProjectIdOfProjectEmployeesCollectionProjectEmployees);
                }
            }
            for (Lfm lfmCollectionLfm : project.getLfmCollection()) {
                Project oldProjectIdOfLfmCollectionLfm = lfmCollectionLfm.getProjectId();
                lfmCollectionLfm.setProjectId(project);
                lfmCollectionLfm = em.merge(lfmCollectionLfm);
                if (oldProjectIdOfLfmCollectionLfm != null) {
                    oldProjectIdOfLfmCollectionLfm.getLfmCollection().remove(lfmCollectionLfm);
                    oldProjectIdOfLfmCollectionLfm = em.merge(oldProjectIdOfLfmCollectionLfm);
                }
            }
            for (Docs docsCollectionDocs : project.getDocsCollection()) {
                Project oldProjectIdOfDocsCollectionDocs = docsCollectionDocs.getProjectId();
                docsCollectionDocs.setProjectId(project);
                docsCollectionDocs = em.merge(docsCollectionDocs);
                if (oldProjectIdOfDocsCollectionDocs != null) {
                    oldProjectIdOfDocsCollectionDocs.getDocsCollection().remove(docsCollectionDocs);
                    oldProjectIdOfDocsCollectionDocs = em.merge(oldProjectIdOfDocsCollectionDocs);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Project project) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Project persistentProject = em.find(Project.class, project.getId());
            ProjectTypes typeIdOld = persistentProject.getTypeId();
            ProjectTypes typeIdNew = project.getTypeId();
            Collection<ProjectEmployees> projectEmployeesCollectionOld = persistentProject.getProjectEmployeesCollection();
            Collection<ProjectEmployees> projectEmployeesCollectionNew = project.getProjectEmployeesCollection();
            Collection<Lfm> lfmCollectionOld = persistentProject.getLfmCollection();
            Collection<Lfm> lfmCollectionNew = project.getLfmCollection();
            Collection<Docs> docsCollectionOld = persistentProject.getDocsCollection();
            Collection<Docs> docsCollectionNew = project.getDocsCollection();
            if (typeIdNew != null) {
                typeIdNew = em.getReference(typeIdNew.getClass(), typeIdNew.getId());
                project.setTypeId(typeIdNew);
            }
            Collection<ProjectEmployees> attachedProjectEmployeesCollectionNew = new ArrayList<ProjectEmployees>();
            for (ProjectEmployees projectEmployeesCollectionNewProjectEmployeesToAttach : projectEmployeesCollectionNew) {
                projectEmployeesCollectionNewProjectEmployeesToAttach = em.getReference(projectEmployeesCollectionNewProjectEmployeesToAttach.getClass(), projectEmployeesCollectionNewProjectEmployeesToAttach.getId());
                attachedProjectEmployeesCollectionNew.add(projectEmployeesCollectionNewProjectEmployeesToAttach);
            }
            projectEmployeesCollectionNew = attachedProjectEmployeesCollectionNew;
            project.setProjectEmployeesCollection(projectEmployeesCollectionNew);
            Collection<Lfm> attachedLfmCollectionNew = new ArrayList<Lfm>();
            for (Lfm lfmCollectionNewLfmToAttach : lfmCollectionNew) {
                lfmCollectionNewLfmToAttach = em.getReference(lfmCollectionNewLfmToAttach.getClass(), lfmCollectionNewLfmToAttach.getId());
                attachedLfmCollectionNew.add(lfmCollectionNewLfmToAttach);
            }
            lfmCollectionNew = attachedLfmCollectionNew;
            project.setLfmCollection(lfmCollectionNew);
            Collection<Docs> attachedDocsCollectionNew = new ArrayList<Docs>();
            for (Docs docsCollectionNewDocsToAttach : docsCollectionNew) {
                docsCollectionNewDocsToAttach = em.getReference(docsCollectionNewDocsToAttach.getClass(), docsCollectionNewDocsToAttach.getId());
                attachedDocsCollectionNew.add(docsCollectionNewDocsToAttach);
            }
            docsCollectionNew = attachedDocsCollectionNew;
            project.setDocsCollection(docsCollectionNew);
            project = em.merge(project);
            if (typeIdOld != null && !typeIdOld.equals(typeIdNew)) {
                typeIdOld.getProjectCollection().remove(project);
                typeIdOld = em.merge(typeIdOld);
            }
            if (typeIdNew != null && !typeIdNew.equals(typeIdOld)) {
                typeIdNew.getProjectCollection().add(project);
                typeIdNew = em.merge(typeIdNew);
            }
            for (ProjectEmployees projectEmployeesCollectionOldProjectEmployees : projectEmployeesCollectionOld) {
                if (!projectEmployeesCollectionNew.contains(projectEmployeesCollectionOldProjectEmployees)) {
                    projectEmployeesCollectionOldProjectEmployees.setProjectId(null);
                    projectEmployeesCollectionOldProjectEmployees = em.merge(projectEmployeesCollectionOldProjectEmployees);
                }
            }
            for (ProjectEmployees projectEmployeesCollectionNewProjectEmployees : projectEmployeesCollectionNew) {
                if (!projectEmployeesCollectionOld.contains(projectEmployeesCollectionNewProjectEmployees)) {
                    Project oldProjectIdOfProjectEmployeesCollectionNewProjectEmployees = projectEmployeesCollectionNewProjectEmployees.getProjectId();
                    projectEmployeesCollectionNewProjectEmployees.setProjectId(project);
                    projectEmployeesCollectionNewProjectEmployees = em.merge(projectEmployeesCollectionNewProjectEmployees);
                    if (oldProjectIdOfProjectEmployeesCollectionNewProjectEmployees != null && !oldProjectIdOfProjectEmployeesCollectionNewProjectEmployees.equals(project)) {
                        oldProjectIdOfProjectEmployeesCollectionNewProjectEmployees.getProjectEmployeesCollection().remove(projectEmployeesCollectionNewProjectEmployees);
                        oldProjectIdOfProjectEmployeesCollectionNewProjectEmployees = em.merge(oldProjectIdOfProjectEmployeesCollectionNewProjectEmployees);
                    }
                }
            }
            for (Lfm lfmCollectionOldLfm : lfmCollectionOld) {
                if (!lfmCollectionNew.contains(lfmCollectionOldLfm)) {
                    lfmCollectionOldLfm.setProjectId(null);
                    lfmCollectionOldLfm = em.merge(lfmCollectionOldLfm);
                }
            }
            for (Lfm lfmCollectionNewLfm : lfmCollectionNew) {
                if (!lfmCollectionOld.contains(lfmCollectionNewLfm)) {
                    Project oldProjectIdOfLfmCollectionNewLfm = lfmCollectionNewLfm.getProjectId();
                    lfmCollectionNewLfm.setProjectId(project);
                    lfmCollectionNewLfm = em.merge(lfmCollectionNewLfm);
                    if (oldProjectIdOfLfmCollectionNewLfm != null && !oldProjectIdOfLfmCollectionNewLfm.equals(project)) {
                        oldProjectIdOfLfmCollectionNewLfm.getLfmCollection().remove(lfmCollectionNewLfm);
                        oldProjectIdOfLfmCollectionNewLfm = em.merge(oldProjectIdOfLfmCollectionNewLfm);
                    }
                }
            }
            for (Docs docsCollectionOldDocs : docsCollectionOld) {
                if (!docsCollectionNew.contains(docsCollectionOldDocs)) {
                    docsCollectionOldDocs.setProjectId(null);
                    docsCollectionOldDocs = em.merge(docsCollectionOldDocs);
                }
            }
            for (Docs docsCollectionNewDocs : docsCollectionNew) {
                if (!docsCollectionOld.contains(docsCollectionNewDocs)) {
                    Project oldProjectIdOfDocsCollectionNewDocs = docsCollectionNewDocs.getProjectId();
                    docsCollectionNewDocs.setProjectId(project);
                    docsCollectionNewDocs = em.merge(docsCollectionNewDocs);
                    if (oldProjectIdOfDocsCollectionNewDocs != null && !oldProjectIdOfDocsCollectionNewDocs.equals(project)) {
                        oldProjectIdOfDocsCollectionNewDocs.getDocsCollection().remove(docsCollectionNewDocs);
                        oldProjectIdOfDocsCollectionNewDocs = em.merge(oldProjectIdOfDocsCollectionNewDocs);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = project.getId();
                if (findProject(id) == null) {
                    throw new NonexistentEntityException("The project with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Project project;
            try {
                project = em.getReference(Project.class, id);
                project.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The project with id " + id + " no longer exists.", enfe);
            }
            ProjectTypes typeId = project.getTypeId();
            if (typeId != null) {
                typeId.getProjectCollection().remove(project);
                typeId = em.merge(typeId);
            }
            Collection<ProjectEmployees> projectEmployeesCollection = project.getProjectEmployeesCollection();
            for (ProjectEmployees projectEmployeesCollectionProjectEmployees : projectEmployeesCollection) {
                projectEmployeesCollectionProjectEmployees.setProjectId(null);
                projectEmployeesCollectionProjectEmployees = em.merge(projectEmployeesCollectionProjectEmployees);
            }
            Collection<Lfm> lfmCollection = project.getLfmCollection();
            for (Lfm lfmCollectionLfm : lfmCollection) {
                lfmCollectionLfm.setProjectId(null);
                lfmCollectionLfm = em.merge(lfmCollectionLfm);
            }
            Collection<Docs> docsCollection = project.getDocsCollection();
            for (Docs docsCollectionDocs : docsCollection) {
                docsCollectionDocs.setProjectId(null);
                docsCollectionDocs = em.merge(docsCollectionDocs);
            }
            em.remove(project);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Project> findProjectEntities() {
        return findProjectEntities(true, -1, -1);
    }

    public List<Project> findProjectEntities(int maxResults, int firstResult) {
        return findProjectEntities(false, maxResults, firstResult);
    }

    private List<Project> findProjectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Project.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Project findProject(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Project.class, id);
        } finally {
            em.close();
        }
    }

    public int getProjectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Project> rt = cq.from(Project.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
