package com.cfs.ResumeAnalyzer_AI.controller;

import org.apache.tika.Tika;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin("*")
public class ResumeController {

	private final ChatClient chatClient;

	private final Tika tika = new Tika();

	public ResumeController(OpenAiChatModel openAiChatModel){
		this.chatClient = ChatClient.create(openAiChatModel);
	}

	@PostMapping("/analyzer")
	public Map<String, Object> analyzer(@RequestParam("file")MultipartFile file) throws Exception
	{
		String content = tika.parseToString(file.getInputStream());

		String prompt = """
				Analyze this resume text:
				%s
				1. Extract key skills
				2. Rate overall resume quality (1-10)
				3. Suggest 4 improvements
				Reply in structured JSON format.
				
				""".formatted(content);

		String aiResponse = chatClient.prompt()
				.user(prompt)
				.call()
				.content();

		return Map.of("analysis", aiResponse);
	}

	@PostMapping("/analyze-ATS")
	public Map<String, Object> analyzeATS(@RequestParam("file")MultipartFile file,
										  @RequestParam("jd") String jobDescription) throws Exception
	{
		String resumeText = tika.parseToString(file.getInputStream());

		String prompt = """
				You are an expert ATS analyser. Compare the response and job description.
				
				----
				Resume:
				%s
				----
				Job Description:
				%s
				----
				Analyze and return a structured JSON with:
				1. "atsScore" (0-100)
				2. "matchedKeywords" (list)
				3. "missingKeywords" (list)
				4. "summary" (short parahgraph)
				
				keep response strictly valid json.
				""".formatted(resumeText,jobDescription);

		String aiResponse = chatClient.prompt()
				.user(prompt)
				.call()
				.content();

		return Map.of("atsReport", aiResponse);
	}
}
