package com.jobportal.application.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jobportal.application.entities.RecruiterProfile;
import com.jobportal.application.entities.Users;
import com.jobportal.application.repository.UsersRepository;
import com.jobportal.application.services.RecruiterProfileService;
import com.jobportal.application.util.FileUploadUtil;

@Controller
@RequestMapping("/recruiter-profile")
public class RecruiterProfileController {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	RecruiterProfileService recruiterProfileService;
	
	@GetMapping("/")
	public String recruiterProfile(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			Users users = usersRepository.findByEmail(currentUsername).orElseThrow(
					() -> new UsernameNotFoundException("Could not " + "found user")
					);
			Optional<RecruiterProfile> recruiterProfile = recruiterProfileService.getOne(users.getUserId());
			if(!recruiterProfile.isEmpty()) {
				model.addAttribute("profile",recruiterProfile.get());
			}
		}
		return "recruiter_profile";
	}
	
	@PostMapping("/addNew")
	public String addNew(RecruiterProfile recruiterProfile, @RequestParam("image") MultipartFile multipartFile, Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not " + "found user"));
            recruiterProfile.setUserId(users);
            recruiterProfile.setUserAccountId(users.getUserId());
		}
		
		model.addAttribute("profile",recruiterProfile);
		String filename="";
		
		if(!multipartFile.getOriginalFilename().equals("")) {
			filename = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
			recruiterProfile.setProfilePhoto(filename);
		}
		
		RecruiterProfile savedUser = recruiterProfileService.addNew(recruiterProfile);
		String uploadDir = "photos/recruiter/" + savedUser.getUserAccountId();
		
		try {
			FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/dashboard/";
	}
}
