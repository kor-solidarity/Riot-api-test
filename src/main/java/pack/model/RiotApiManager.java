package pack.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.Summoner.Summoner;

@Repository
public class RiotApiManager {
	
	RiotApi api;
	private final String KEY = "RGAPI-318533dd-c968-4019-8f0b-7d9ead363547";
	@Autowired
	public RiotApiManager(RiotApi api) {
		this.api = api;
		api.setKey(KEY);
		api.setRegion(Region.KR);
	}
	
	public Summoner getSummonerByName(String name) throws RiotApiException{
		return api.getSummonerByName(name);
	}
	
	public HashMap<String, Object> setLeagueData(Long id){
		HashMap<String, Object> map = new HashMap<>();
		try {
			List<League> leaguedata = api.getLeagueEntryBySummoner(id);
			for (int i = 0; i < leaguedata.size(); i++) {
				map.put("id", id);
				map.put("name", leaguedata.get(i).getName());
				map.put("queue", leaguedata.get(i).getQueue());
				map.put("tier", leaguedata.get(i).getTier());
				map.put("entries", leaguedata.get(i).getEntries().get(i).getPlayerOrTeamId());
				map.put("entriedto", leaguedata.get(i).getEntries().get(i));
			}
		} catch (RiotApiException e) {
			map.put("Error", "데이터를 찾지 못했습니다.");
			return map;
		}
		return map;
	}
	
	
}
