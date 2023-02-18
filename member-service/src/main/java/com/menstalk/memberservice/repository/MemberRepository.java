package com.menstalk.memberservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.menstalk.memberservice.domain.Member;
import com.menstalk.memberservice.dto.PartyResponse;

import feign.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

	List<Member> findMemberIdsByPartyId(Long partyId);

	@Query("SELECT NEW com.example.PartyResponse(p.id, u.id, p.name, COUNT(m.id), p.createTime) "
			+ "FROM Party p INNER JOIN p.users u LEFT JOIN p.members m WHERE u.id = :userId GROUP BY p.id")
	List<PartyResponse> findPartysByUserId(@Param("userId") Long userId);
	/**
	 * 首先，@Query注解用于在MemberRepository接口中定义自定义查询方法。在括号中，我们提供了一条JPQL（Java Persistence
	 * Query Language）查询语句来检索与特定用户相关的所有派对的信息。
	 * 这里的SELECT语句用于选择所需的数据，这里选择的是一个PartyResponse对象。因为我们想要返回一个PartyResponse对象的列表，
	 * 所以这个SELECT语句应该选择一个PartyResponse对象列表。我们使用NEW关键字来创建一个PartyResponse对象，同时提供了构造函数中所需的所有参数。
	 * SELECT NEW com.example.PartyResponse(p.id, u.id, p.name, COUNT(m.id),
	 * p.createTime)
	 * 这个SELECT语句选择了派对ID、用户ID、派对名称、参与成员数量和创建时间，并将它们传递给PartyResponse类的构造函数，以创建一个PartyResponse对象。
	 * 然后，我们使用FROM关键字指定要从哪个实体类（在这个例子中是Party）中检索数据。 FROM Party p 我们使用INNER JOIN和LEFT
	 * JOIN语句来连接其他实体类（在这个例子中是User和Member），以检索有关派对、用户和成员之间关系的更多信息。 INNER JOIN p.users
	 * u LEFT JOIN p.members m 在这个例子中，我们使用INNER JOIN来检索派对实体和用户实体之间的关系，同时使用LEFT
	 * JOIN来检索派对实体和成员实体之间的关系。 最后，我们使用WHERE子句来限制查询结果，以仅返回与特定用户相关的派对的信息。 WHERE u.id =
	 * :userId 这个WHERE子句限制了查询结果，以仅返回与特定用户ID相等的用户相关的派对。 最后，我们使用GROUP
	 * BY子句将结果分组为一组，以便我们可以获得每个派对的唯一结果。 GROUP BY p.id
	 * 总之，这个自定义查询语句旨在选择有关与特定用户相关的所有派对的信息，并将它们映射到PartyResponse对象中。
	 */
}
