package com.ProjectHub.service;

import com.ProjectHub.entities.Project;
import com.ProjectHub.entities.StudentProfile;
import com.ProjectHub.entities.TeacherProfile;
import com.ProjectHub.model.*;
import com.ProjectHub.repository.ProjectRepository;
import com.ProjectHub.repository.TeacherRepository;
import com.ProjectHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProjectProfileService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherRepository teacherRepository;

    ProjectProfileModel getProjectCardDetails(Long projectID) {
        ProjectProfileModel projectProfileModel = new ProjectProfileModel();
        Project project = projectRepository.findByProjectId(projectID);

        if (project != null) {
            projectProfileModel.setProjectId(project.getProjectId());
            projectProfileModel.setProjectDomain(project.getProjectDomain());
            projectProfileModel.setProjectTitle(project.getProjectTitle());
            projectProfileModel.setDescription(project.getDescription());
            projectProfileModel.setProjectTag1(project.getProjectTag1());
            projectProfileModel.setProjectTag2(project.getProjectTag2());
            projectProfileModel.setProjectTag3(project.getProjectTag3());
            return projectProfileModel;
        }
        return null;
    }

    public Project updateProject(ProjectSubmissionModel _project) {
        Project project = projectRepository.findByProjectId(_project.getProjectID());
        project.setProjectTitle(_project.getProjectTitle());
        project.setDescription(_project.getDescription());
        project.setProjectDomain(_project.getProjectDomain());
        project.setImageUrl(_project.getImageUrl());
        project.setPptUrl(_project.getPptUrl());
        project.setPaperUrl(_project.getPaperUrl());
        project.setProjectTag1(_project.getProjectTag1());
        project.setProjectTag2(_project.getProjectTag2());
        project.setProjectTag3(_project.getProjectTag3());
        projectRepository.save(project);
        return project;
    }

    private Map<String, String> mapStudentDetails(StudentProfile studentProfile) {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(studentProfile.getUsername()));
        map.put("name", studentProfile.getFirstName() + " " + studentProfile.getLastName());
        return map;
    }

    public ProjectDetails getProjectDetails(Long id) {
        Project project = projectRepository.findByProjectId(id);

        TeacherProfile guide = teacherRepository.findById(String.valueOf(project.getGuideID())).get();
        String guideName = guide.getFirstName() + " " + guide.getLastName();

        Map<String, String> guideMap = new HashMap<>();
        guideMap.put("id", String.valueOf(project.getGuideID()));
        guideMap.put("name", guideName);

        StudentProfile student1 = userRepository.findByUsername(String.valueOf(project.getStudent1id())).get();
        StudentProfile student2 = userRepository.findByUsername(String.valueOf(project.getStudent2id())).get();
        StudentProfile student3 = userRepository.findByUsername(String.valueOf(project.getStudent3id())).get();
        StudentProfile student4 = userRepository.findByUsername(String.valueOf(project.getStudent4id())).get();
        StudentProfile student5 = project.getStudent5id() != null ? userRepository.findByUsername(String.valueOf(project.getStudent5id())).get() : null;

        String[] tokens = project.getProjectInfo().split("#");
        Map<String, String> projectInfo = new HashMap<>();
        projectInfo.put("yearInfo", "EDAI " + tokens[0] + " " + tokens[1]);
        projectInfo.put("groupInfo", tokens[2]);

        ProjectDetails projectDetails = new ProjectDetails(id,
                project.getProjectTitle(),
                project.getProjectDomain(),
                project.getDescription(),
                project.getProjectTag1(), project.getProjectTag2(), project.getProjectTag3(),
                project.getImageUrl(), project.getPptUrl(), project.getPaperUrl(),
                project.getClosed(),
                projectInfo,
                guideMap,
                mapStudentDetails(student1),
                mapStudentDetails(student2),
                mapStudentDetails(student3),
                mapStudentDetails(student4),
                student5 == null ? null : mapStudentDetails(student5),
                project.getDeadline()
        );
        return projectDetails;
    }

//    public List<TeacherOngoingProjectModel> getTeacherOngoingCardDetails(String teacherID) {
//        List<Project> projectList = projectRepository.findAllByTeacherId(Long.valueOf(teacherID), "0");
//
//        List<TeacherOngoingProjectModel> teacherOngoingProjectModelList = new ArrayList<>();
//        for (Project project : projectList) {
//            teacherOngoingProjectModelList.add(new TeacherOngoingProjectModel(project));
//        }
//
//        return teacherOngoingProjectModelList;
//    }

    private void updateProjectInStudent(Long projectId, String studentId, int semNum) {
        StudentProfile student = userRepository.findByUsername(studentId).get();
        switch (semNum) {
            case 1:
                student.setProject1id(projectId);
                break;
            case 2:
                student.setProject2id(projectId);
                break;
            case 3:
                student.setProject3id(projectId);
                break;
            case 4:
                student.setProject4id(projectId);
                break;
            case 5:
                student.setProject5id(projectId);
                break;
            case 6:
                student.setProject6id(projectId);
                break;
            case 7:
                student.setProject7id(projectId);
                break;
            case 8:
                student.setProject8id(projectId);
                break;
        }
        userRepository.save(student);
    }

    public void insertProject(NewProjectModel newProjectModel) {
        Project project = new Project();

        project.setProjectInfo(newProjectModel.getProjectInfo());
        project.setGuideID(newProjectModel.getGuideID());
        project.setStudent1id(newProjectModel.getStudent1ID());
        project.setStudent2id(newProjectModel.getStudent2ID());
        project.setStudent3id(newProjectModel.getStudent3ID());
        project.setStudent4id(newProjectModel.getStudent4ID());
        project.setStudent5id(newProjectModel.getStudent5ID());
        project.setDeadline(newProjectModel.getDeadline());

        projectRepository.save(project);

        long pk = project.getProjectId();
        //        System.out.println("Auto generated key: " + pk);

        /* determine project column number */
        Map<String, Integer> yearMap = new HashMap<>();
        yearMap.put("FY", 0);
        yearMap.put("SY", 1);
        yearMap.put("TY", 2);
        yearMap.put("BTech", 3);

        String[] separateInfo = newProjectModel.getProjectInfo().split("#");
        int sem = Integer.parseInt(separateInfo[1].split(" ")[1]);
        int year = yearMap.get(separateInfo[2].split("-")[0]);
        //        System.out.println(year + " " + sem + " " + (2 * year + sem));

        /* assign project id to all 4/5 students */
        int semNum = 2 * year + sem;
        updateProjectInStudent(pk, String.valueOf(newProjectModel.getStudent1ID()), semNum);
        updateProjectInStudent(pk, String.valueOf(newProjectModel.getStudent2ID()), semNum);
        updateProjectInStudent(pk, String.valueOf(newProjectModel.getStudent3ID()), semNum);
        updateProjectInStudent(pk, String.valueOf(newProjectModel.getStudent4ID()), semNum);
        if (newProjectModel.getStudent5ID() != null)
            updateProjectInStudent(pk, String.valueOf(newProjectModel.getStudent5ID()), semNum);

    }

    public void updateDeadline(ProjectDeadlineModel projectDeadlineModel) {
        Project project = projectRepository.findByProjectId(projectDeadlineModel.getProjectID());
        project.setDeadline(projectDeadlineModel.getDeadline());
        projectRepository.save(project);
    }

    public void closeProject(Long projectID) {
        Project project = projectRepository.findByProjectId(projectID);
        project.setClosed("1");
        projectRepository.save(project);
    }

}
