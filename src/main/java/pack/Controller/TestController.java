package pack.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.PlatformId;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.ChampionMastery.ChampionMastery;
import net.rithms.riot.dto.MatchList.MatchList;
import net.rithms.riot.dto.Stats.PlayerStatsSummary;
import net.rithms.riot.dto.Stats.PlayerStatsSummaryList;
import net.rithms.riot.dto.Summoner.Summoner;
import pack.model.SummonerDao;
//{"id":1315619,"revisionDate":1472390698000,"summonerLevel":30,"profileIconId":0,"name":"서폿오알정글"}

@CrossOrigin(origins="*")
@Controller
public class TestController {
	//aggregatedStats
//	RiotApi api = new RiotApi("RGAPI-318533dd-c968-4019-8f0b-7d9ead363547", Region.KR);
//	PlayerStatsSummaryList summary = api.getPlayerStatsSummary(1315619);
//	
	@Autowired
	@Qualifier("summonerDao")
	SummonerDao summonerDao;
	
	@RequestMapping("user")
	@ResponseBody
	public Object getlist() {
		return summonerDao.selectSummoner();
	}
	@RequestMapping("user/{username}")
	@ResponseBody
	public Object getlist1(@PathVariable("username")String username) {
		Map<String, Object> map = new HashMap<>();
		SummonerBean bean = new SummonerBean();
		bean.setName(username);
		Summoner summoner = summonerDao.selectsearchData(bean);
		try {
			map.put("userData", summoner);
			map.put("leagueData", summonerDao.selectLeagueData(summoner.getId()));
			summonerDao.selectLeagueData(summoner.getId());
		} catch (Exception e) {
			map.put("data","데이터를 가져오는데 실패함.");
			return map;
		}
		return map;
	}
	
}
