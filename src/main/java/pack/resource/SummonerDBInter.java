package pack.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.League.LeagueEntry;
import net.rithms.riot.dto.Summoner.Summoner;
import pack.Controller.SummonerBean;
import pack.model.LeagueDto;

public interface SummonerDBInter {
	@Select("select * from summoner")
	public List<Summoner> selectSummoner();
	
	@Select("select * from summoner where name=#{name}")
	public Summoner selectsearchData(SummonerBean bean);
	
	@Select("select * from league where id=#{id}")
	public LeagueDto selectLeagueData(Long id);
	@Select("select * from entries where playerOrTeamId=#{entry}")
	public List<LeagueEntry> selectEntryData(Long entry);

	@Insert("insert into summoner values(#{id}, #{revisionDate},#{summonerLevel},#{profileIconId},#{name})")
	public int insertsummoner(Summoner bean);
	
	@Insert("insert into league values(#{id}, #{name},#{tier},#{queue},#{entries})")
	public int insertleague(Map<String, Object> map);
	
	@Insert("insert into entries values(#{playerOrTeamId},#{playerOrTeamName} ,#{division} ,#{leaguePoints} ,#{wins} ,#{losses},'NONE',#{isHotStreak},#{isVeteran},#{isFreshBlood},#{isInactive})")
	public int insertentrys(LeagueEntry entry);
}
