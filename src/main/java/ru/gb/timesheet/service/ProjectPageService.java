package ru.gb.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.page.ProjectPageDto;
import ru.gb.timesheet.page.TimesheetPageDto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectPageService {

  private final ProjectService projectService;

  public List<ProjectPageDto> findAll() {
    return projectService.getAll().stream()
      .map(this::convert)
      .toList();
  }

  public Optional<ProjectPageDto> findById(Long id) {
    return projectService.getById(id) // Optional<Timesheet>
      .map(this::convert);
  }

  private ProjectPageDto convert(Project project) {

    ProjectPageDto projectPageParameters = new ProjectPageDto();
    projectPageParameters.setId(String.valueOf(project.getId()));
    projectPageParameters.setName(project.getName());
    return projectPageParameters;
  }

}
