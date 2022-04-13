package study.datajpa.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

public class MemberSpec {
	
	public static Specification<Member> teamName(final String teamName){
		return new Specification<Member>() {

			@Override
			public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				if(StringUtils.isEmpty(teamName)) {
					return null;
				}
				
				Join<Member, Team> t = root.join("team", JoinType.INNER);
				
				return builder.equal(t.get("name"), teamName);
			}
			
		};
	}
	
	public static Specification<Member> userName(final String username){
		return ( Specification<Member>)( root, query, builder) -> 
			 builder.equal(root.get("username"), username);
	};
}
