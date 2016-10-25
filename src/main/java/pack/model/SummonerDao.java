package pack.model;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.League.LeagueEntry;
import net.rithms.riot.dto.Summoner.Summoner;
import pack.Controller.SummonerBean;
import pack.resource.SummonerDBInter;

@Repository
public class SummonerDao  {
	
	
	@Autowired
	private SummonerDBInter summonerDBInter;
	
	@Autowired
	RiotApiManager riotApiManager;
	

	public Summoner selectsearchData(SummonerBean bean) throws DataAccessException {
		Summoner summoner = summonerDBInter.selectsearchData(bean);
		if (summoner == null) {
			int re = 0;
			try {
				summoner=riotApiManager.getSummonerByName(bean.getName());
				HashMap<String , Object> leaguemap = riotApiManager.setLeagueData(summoner.getId());
				re +=summonerDBInter.insertleague(leaguemap);
				re +=summonerDBInter.insertentrys((LeagueEntry)leaguemap.get("entriedto"));
				re +=summonerDBInter.insertsummoner(summoner);
			} catch (RiotApiException e) {
				return null;
			}
			if (re >= 3) {return summoner;}else{return null;}
		}else{
			return summoner;			
		}
	}
	
	public LeagueDto selectLeagueData(Long id){
		LeagueDto league = summonerDBInter.selectLeagueData(id);
		league.setEntrylist(summonerDBInter.selectEntryData(league.getEntries()));
		return league;
	}

	public List<Summoner> selectSummoner() throws DataAccessException{
		return summonerDBInter.selectSummoner();
	}
	
	
	
	
	
	
}
