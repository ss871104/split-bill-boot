package com.menstalk.memberservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.menstalk.memberservice.domain.Member;
import com.menstalk.memberservice.dto.BillAddedRequest;
import com.menstalk.memberservice.dto.BillDetailType;
import com.menstalk.memberservice.dto.PartyResponse;
import com.menstalk.memberservice.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service //用於標注某個類是服務類，告訴Spring容器需要將該類實例化，並且可以被其他類所引用。
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	// private final MemberMapper memberMapper;

	@Override
	public List<Member> findMembersByPartyId(Long partyId) {
		List<Member> Memberlist = new ArrayList<>();
		Memberlist = memberRepository.findMemberIdsByPartyId(partyId);
		return Memberlist;
	}

	@Override
	public List<PartyResponse> findPartysByUserId(Long userId) {

		List<PartyResponse> PartyResponseList = new ArrayList<>();
		PartyResponseList = memberRepository.findPartysByUserId(userId);
		return PartyResponseList;
	}
	@Override
	public boolean addMembers(Member member) {
		if (member.getMemberId() == null) {
			Member newMember = memberRepository.save(member);
			return true;
		}
		return false;

	}

	@Override
	public boolean updateMember(Member member) {
		if (memberRepository.existsById(member.getMemberId())) {
			memberRepository.save(member);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteMember(Long memberId) {
		try {
			memberRepository.deleteById(memberId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateBalanceAdd(List<BillAddedRequest> list) {

		Set<Long> memberIds = list.stream().map(x -> x.getMemberId()).collect(Collectors.toSet());
		/**
		 * 利用 Java 8 的 Stream API 對一個 List<BillAddedRequest> 做處理，目的是把 List
		 * 中每一個元素的memberID 取出來， 並且把這些 member ID 存入一個Set<Long> 中。 使用 list.stream() 來建立一個
		 * Stream 物件，之後再依序呼叫 map(x ->x.getMemberId())、collect(Collectors.toSet()) 來處理這個
		 * Stream 物件。 其中： map(x -> x.getMemberId()) 的意思是，對 Stream 中的每一個元素 x 呼叫
		 * x.getMemberId() 方法，並把回傳值當作新的 Stream 中的元素。 collect(Collectors.toSet()) 的意思是，把
		 * Stream 中的所有元素收集起來，並存入一個 Set 中。 由於 Set 的特性是不能有重複元素，所以最後的結果會是所有 Member 的 member
		 * ID，去除了重複的部分。
		 */
		List<Member> memberListOrigin = memberRepository.findAllById(memberIds);
		/**
		 * 程式碼先透過 findAllById(memberIds) 從資料庫取得符合 memberIds 中 id 的會員資料，取得的資料會以
		 * List<Member> 的形式儲存在 memberListOrigin 中。 從 memberRepository 中獲取 memberIds
		 * 這個集合中所有成員的資料，並返回 List<Member> 類型的結果。
		 * 此處，findAllById()方法接受一個Iterable類型的參數，所以我們需要將memberIds轉換為Iterable類型。
		 * 在這裡，我們可以直接傳遞Set類型的memberIds，因為Set是Iterable的一種實作。
		 * findAllById()方法將返回一個包含所有Member的List。如果在MemberRepository中使用了@Query，
		 * 則findAllById()方法可能會根據@Query中的條件進行限制，返回符合條件的Member。如果沒有使用@Query，則會返回數據表中的所有Member。
		 */
		Map<Long, Member> memberMapOrigin = new HashMap<>();
		memberListOrigin.forEach(x -> memberMapOrigin.put(x.getMemberId(), x));
		/**
		 * 這段程式碼是要將 List<Member> 轉換為 Map<Long, Member>，其中以 Member 物件的 memberId 當作 Map 中的
		 * key，Member 物件本身當作 Map 中的 value。 建立一個空的 HashMap 物件 memberMapOrigin，並使用 forEach
		 * 方法將 memberListOrigin 中的元素一一放進 memberMapOrigin 中，放置的方式是以 Member 物件的 memberId
		 * 當作 key，Member 物件本身當作 value這裡使用了 lambda 表達式，其中 x 是 memberListOrigin
		 * 中的元素，執行時會依序取出每個元素，並以 x.getMemberId() 當作 key，x 當作 value，使用 put 方法將元素放進
		 * memberMapOrigin 中。 最後，memberMapOrigin 中的資料結構會是以 Long 型態的 id 當作 key，對應到 Member
		 * 物件的資料。
		 **/

		list.forEach(x -> {
			if (x.getBillDetailType() == BillDetailType.EXPENSE) {
				memberMapOrigin.put(x.getMemberId(), memberMapOrigin.get(x.getMemberId()))
						.setBalance(memberMapOrigin.get(x.getMemberId()).getBalance() - x.getAmount());
			} else {
				memberMapOrigin.put(x.getMemberId(), memberMapOrigin.get(x.getMemberId()))
						.setBalance(memberMapOrigin.get(x.getMemberId()).getBalance() + x.getAmount());
			}
		});
		// 從REQUEST里如果BillDetailType是EXPENSE的話，從memberListOrigin中抓出原本的BALANCE去減掉REQUEST中對應到的MEMBERiD的amount，反之亦然
		List<Member> memberList = memberMapOrigin.entrySet().stream().map(x -> x.getValue())
				.collect(Collectors.toList());
		// 把map的value(Member)轉成list
		memberRepository.saveAll(memberList);
		return true;
	}

	@Override
	public boolean updateBalanceDelete(List<BillAddedRequest> list) {

		Set<Long> memberIds = list.stream().map(x -> x.getMemberId()).collect(Collectors.toSet());
		/**
		 * 利用 Java 8 的 Stream API 對一個 List<BillAddedRequest> 做處理，目的是把 List
		 * 中每一個元素的memberID 取出來， 並且把這些 member ID 存入一個Set<Long> 中。 使用 list.stream() 來建立一個
		 * Stream 物件，之後再依序呼叫 map(x ->x.getMemberId())、collect(Collectors.toSet()) 來處理這個
		 * Stream 物件。 其中： map(x -> x.getMemberId()) 的意思是，對 Stream 中的每一個元素 x 呼叫
		 * x.getMemberId() 方法，並把回傳值當作新的 Stream 中的元素。 collect(Collectors.toSet()) 的意思是，把
		 * Stream 中的所有元素收集起來，並存入一個 Set 中。 由於 Set 的特性是不能有重複元素，所以最後的結果會是所有 Member 的 member
		 * ID，去除了重複的部分。
		 */
		List<Member> memberListOrigin = memberRepository.findAllById(memberIds);
		/**
		 * 程式碼先透過 findAllById(memberIds) 從資料庫取得符合 memberIds 中 id 的會員資料，取得的資料會以
		 * List<Member> 的形式儲存在 memberListOrigin 中。 從 memberRepository 中獲取 memberIds
		 * 這個集合中所有成員的資料，並返回 List<Member> 類型的結果。
		 * 此處，findAllById()方法接受一個Iterable類型的參數，所以我們需要將memberIds轉換為Iterable類型。
		 * 在這裡，我們可以直接傳遞Set類型的memberIds，因為Set是Iterable的一種實作。
		 * findAllById()方法將返回一個包含所有Member的List。如果在MemberRepository中使用了@Query，
		 * 則findAllById()方法可能會根據@Query中的條件進行限制，返回符合條件的Member。如果沒有使用@Query，則會返回數據表中的所有Member。
		 */
		Map<Long, Member> memberMapOrigin = new HashMap<>();
		memberListOrigin.forEach(x -> memberMapOrigin.put(x.getMemberId(), x));
		/**
		 * 這段程式碼是要將 List<Member> 轉換為 Map<Long, Member>，其中以 Member 物件的 memberId 當作 Map 中的
		 * key，Member 物件本身當作 Map 中的 value。 建立一個空的 HashMap 物件 memberMapOrigin，並使用 forEach
		 * 方法將 memberListOrigin 中的元素一一放進 memberMapOrigin 中，放置的方式是以 Member 物件的 memberId
		 * 當作 key，Member 物件本身當作 value這裡使用了 lambda 表達式，其中 x 是 memberListOrigin
		 * 中的元素，執行時會依序取出每個元素，並以 x.getMemberId() 當作 key，x 當作 value，使用 put 方法將元素放進
		 * memberMapOrigin 中。 最後，memberMapOrigin 中的資料結構會是以 Long 型態的 id 當作 key，對應到 Member
		 * 物件的資料。
		 **/

		list.forEach(x -> {
			if (x.getBillDetailType() == BillDetailType.EXPENSE) {
				memberMapOrigin.put(x.getMemberId(), memberMapOrigin.get(x.getMemberId()))
						.setBalance(memberMapOrigin.get(x.getMemberId()).getBalance() + x.getAmount());
			} else {
				memberMapOrigin.put(x.getMemberId(), memberMapOrigin.get(x.getMemberId()))
						.setBalance(memberMapOrigin.get(x.getMemberId()).getBalance() - x.getAmount());
			}
		});
		// 從REQUEST里如果BillDetailType是EXPENSE的話，從memberListOrigin中抓出原本的BALANCE去減掉REQUEST中對應到的MEMBERiD的amount，反之亦然
		List<Member> memberList = memberMapOrigin.entrySet().stream().map(x -> x.getValue())
				.collect(Collectors.toList());
		// 把map的value(Member)轉成list
		memberRepository.saveAll(memberList);
		return true;
	}

	@Override
	public Long countMember(Long partyId) {
		
		return memberRepository.countMember(partyId);
	}

	
}
