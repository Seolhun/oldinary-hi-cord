package com.brain.home.controller.music;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brain.home.common.CommonFn;
import com.brain.home.entity.music.Music;
import com.brain.home.entity.music.MusicList;
import com.brain.home.entity.music.MusicType;
import com.brain.home.service.music.MusicService;

@Controller
@RequestMapping("/music")
public class MusicController {

	@Autowired
	private MusicService musicService;

	@Autowired
	private CommonFn commonFn;
	
	private static final Logger log = LoggerFactory.getLogger(MusicController.class);
	
	private final String UPLOAD_LOCATION = "/Users/HunSeol/Desktop/shooney/file/";

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listMusics(ModelMap model) {
		ArrayList<Music> musics = (ArrayList<Music>) musicService.findAllMusics();
		model.addAttribute("musics", musics);
		return "vies/music/list";
	}

	@RequestMapping(value = { "/get" }, method = RequestMethod.GET)
	public String getMusic(ModelMap model) throws IOException {
		log.info("TEST : 음악파일을 가져옵니다.");
		JSONObject obj = new JSONObject();
		String domain="";
		for(MusicType m : MusicType.values()){
			domain=m.getMusicType();
			Document doc = Jsoup.connect("http://music.naver.com/listen/top100.nhn?domain="+domain).get();
			// 태그 혹은 속성 명으로 가져올 수 있다.
			Elements titleEl = doc.select(".name a .ellipsis");
			Elements singerEl = doc.select("._artist .ellipsis");
			Elements imageEl = doc.select(".name a img");
			JSONArray titleList = new JSONArray();
			JSONArray singerList = new JSONArray();
			JSONArray imageList = new JSONArray();
			for (Element e : titleEl) {
				titleList.add(e.html());
				System.out.println(e.html());
			}
			for (Element e : singerEl) {
				singerList.add(e.html());
				System.out.println(e.html());
			}
			for (Element e : imageEl) {
				if (!e.absUrl("src").substring(e.absUrl("src").length() - 3, e.absUrl("src").length()).equals("gif")) {
					imageList.add(e.absUrl("src"));
					System.out.println(e.absUrl("src"));
				}
			}
			obj.put("singer", singerList);
			obj.put("title", titleList);
			obj.put("image", imageList);
	
			try {
				SimpleDateFormat formatter =new SimpleDateFormat("yyyy.MM.dd HH:mm",Locale.KOREA);
				Date currentTime =new Date();
				//FileWriter file = new FileWriter("/Users/hunseol/Desktop/test.txt");
				FileWriter file = new FileWriter(UPLOAD_LOCATION+domain+(currentTime)+".txt");
				file.write(obj.toJSONString());
				file.flush();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.print(obj);
		return "redirect:/music";
	}

	@RequestMapping(value = { "/set" }, method = RequestMethod.GET)
	public String newMusics(ModelMap model) {
		MusicList music = new MusicList();
		JSONParser parser = new JSONParser();
		try {
			//Object obj = parser.parse(new FileReader("/Users/HunSeol/Desktop/test.txt"));
			Object obj = parser.parse(new FileReader("/Users/hunseol/Desktop/FileSaver/test.txt"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray msg1 = (JSONArray) jsonObject.get("singer");
			JSONArray msg2 = (JSONArray) jsonObject.get("title");
			JSONArray msg3 = (JSONArray) jsonObject.get("image");
			Iterator<String> iterator1 = msg1.iterator();
			Iterator<String> iterator2 = msg2.iterator();
			Iterator<String> iterator3 = msg3.iterator();
			while (iterator1.hasNext()) {
				Music music2 = new Music();
				music2.setSinger(iterator1.next());
				music2.setTitle(iterator2.next());
				music2.setImage(iterator3.next());
				musicService.saveMusic(music2);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		model.addAttribute("music", music);
		model.addAttribute("edit", false);
		return "redirect:/music/list";
	}
}