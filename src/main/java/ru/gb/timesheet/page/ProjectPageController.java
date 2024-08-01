package ru.gb.timesheet.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.timesheet.service.ProjectPageService;
import ru.gb.timesheet.service.TimesheetPageService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/home/project")
@RequiredArgsConstructor
public class ProjectPageController {

  private final ProjectPageService service;

  // GET /home/project/{id}
  @GetMapping("/{id}")
  public String getProjectPage(@PathVariable Long id, Model model) {
    Optional<ProjectPageDto> projectOpt = service.findById(id);
    if (projectOpt.isEmpty()) {
      throw new NoSuchElementException();
    }
    model.addAttribute("project", projectOpt.get());
    return "project-page.html";
  }

}
