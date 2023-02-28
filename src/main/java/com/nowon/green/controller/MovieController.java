package com.nowon.green.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowon.green.domain.dto.movie.DailyBoxOfficeDTO;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;

@Controller
public class MovieController {
	
	@Value("${movieKey}")
	private String key;
	
	@GetMapping("/common/movie/daily-box-office")
	public String dailyBoxOffice(HttpServletRequest request) throws OpenAPIFault, Exception {
		
		LocalDate yesterday=LocalDate.now().minusDays(1);
		request.setAttribute("basic", yesterday);
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyyMMdd");
		// 파라메터 설정
		String targetDt = request.getParameter("targetDt")==null?yesterday.format(formatter):request.getParameter("targetDt");			//조회일자
		String itemPerPage = request.getParameter("itemPerPage")==null?"10":request.getParameter("itemPerPage");		//결과row수
		String multiMovieYn = request.getParameter("multiMovieYn")==null?"":request.getParameter("multiMovieYn");		//“Y” : 다양성 영화 “N” : 상업영화 (default : 전체)
		String repNationCd = request.getParameter("repNationCd")==null?"":request.getParameter("repNationCd");			//“K: : 한국영화 “F” : 외국영화 (default : 전체)
		String wideAreaCd = request.getParameter("wideAreaCd")==null?"":request.getParameter("wideAreaCd");				//“0105000000” 로서 조회된 지역코드

		
		// KOBIS 오픈 API Rest Client를 통해 호출
	    KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);

		// 일일 박스오피스 서비스 호출 (boolean isJson, String targetDt, String itemPerPage,String multiMovieYn, String repNationCd, String wideAreaCd)
	    String dailyResponse = service.getDailyBoxOffice(true,targetDt,itemPerPage,multiMovieYn,repNationCd,wideAreaCd);

		// Json 라이브러리를 통해 Handling
		ObjectMapper mapper = new ObjectMapper();
		DailyBoxOfficeDTO dailyResult = mapper.readValue(dailyResponse, DailyBoxOfficeDTO.class);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(dailyResult.getBoxOfficeResult().getDailyBoxOfficeList());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		request.setAttribute("list", dailyResult.getBoxOfficeResult().getDailyBoxOfficeList());
		
		return "movie/daily-box-office";
	}
}
