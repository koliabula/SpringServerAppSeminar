package ru.gb.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service // то же самое, что и Component
public class TimesheetService {
  private final TimesheetRepository timesheetRepository;
  private final ProjectRepository projectRepository;



  public TimesheetService(TimesheetRepository timesheetRepository, ProjectRepository projectRepository) {
    this.timesheetRepository = timesheetRepository;
    this.projectRepository = projectRepository;
  }

  public Optional<Timesheet> getById(Long id) {
    return timesheetRepository.getById(id);
  }

  public List<Timesheet> getAll() {
    return timesheetRepository.getAll();
  }

  public Timesheet create(Timesheet timesheet) {
    // Проверяем существует ли проект с таким id
    if (Objects.isNull(timesheet.getProjectId())) {
      throw new IllegalArgumentException("projectId must not be null");
    }

    if (projectRepository.getById(timesheet.getProjectId()).isEmpty()) {
      throw new NoSuchElementException("Project with id " + timesheet.getProjectId() + " does not exists");
    }

    timesheet.setCreatedAt(LocalDate.now());
    return timesheetRepository.create(timesheet);
  }


  public void delete(Long id) {
    timesheetRepository.delete(id);
  }

}
