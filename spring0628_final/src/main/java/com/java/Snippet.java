package com.java;

public class Snippet {
	<!DOCTYPE mapper
	    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
	    "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
	
	<mapper namespace="com.java.mapper.BoardMapper">
	   <!-- 게시글 전체개수 -->
	   <select id="selectListCount" resultType="int">
	       select count(*) from board
	       <if test="category == 'all'">
			   where btitle like '%'||#{s_word}||'%' or bcontent like '%'||#{s_word}||'%'
		   </if>
		   <if test="category == 'btitle'">
			   where btitle like '%'||#{s_word}||'%'
		   </if>
		   <if test="category == 'bcontent'">
			   where bcontent like '%'||#{s_word}||'%'
		   </if>
	       
	   </select>
	   
	   
	   <!-- 게시글 전체가져오기 -->
	   <select id="selectAll" resultType="com.java.dto.BoardDto">
	         select * from (select rownum rnum,a.* from 
		     (select * from board 
	        
			<if test="category == 'all'">
			     where btitle like '%'||#{s_word}||'%' or bcontent like '%'||#{s_word}||'%'
			</if>
			<if test="category == 'btitle'">
			     where btitle like '%'||#{s_word}||'%'
			</if>
			<if test="category == 'bcontent'">
			     where bcontent like '%'||#{s_word}||'%'
			</if>
			  
			<!-- 공통영역 -->
			   <![CDATA[
	           order by bgroup desc,bstep asc) a)
			   where rnum >= #{startRow} and rnum <= #{endRow}
			   ]]>
	   </select> 
	   
	   <!-- 게시글 1개가져오기 -->
	   <select id="selectOne" resultType="com.java.dto.BoardDto">
	      select * from board where bno=#{bno}
	   </select>
	
	   <!-- 게시글 이전글 -->
	   <select id="selectPrevOne" resultType="com.java.dto.BoardDto">
	      SELECT * FROM board WHERE bno=
	      (SELECT prevDto FROM (SELECT bno,lag(bno,1,-1) over(ORDER BY bgroup desc,bstep asc) as prevDto 
	      from board ORDER BY bgroup desc, bstep asc) where bno=#{bno})
	   </select>
	
	   <!-- 게시글 다음글 -->
	   <select id="selectNextOne" resultType="com.java.dto.BoardDto">
	      SELECT * FROM board WHERE bno=
	      (SELECT nextDto FROM (SELECT bno,lead(bno,1,-1) over(ORDER BY bgroup desc,bstep asc) as nextDto 
	      from board ORDER BY bgroup desc, bstep asc) where bno=#{bno})
	   </select>
	   
	   <!-- 게시글 1개저장 -->
	   <insert id="insertOne">
	      insert into board values (board_seq.nextval,#{id},#{btitle},#{bcontent},
	      sysdate,0,board_seq.currval,0,0,#{bfile})
	   </insert>
	   
	   <!-- 게시글 1개삭제 -->
	   <delete id="deleteOne">
	      delete board where bno=#{bno}
	   </delete>
	   
	   <!-- 게시글 1개수정 -->
	   <update id="updateOne">
	      update board set btitle=#{btitle},bcontent=#{bcontent},bfile=#{bfile} 
	      where bno=#{bno}
	   </update>
	   
	   <update id="updateBstepCount">
	      update board set bstep=bstep+1 where bgroup=#{bgroup} and bstep>#{bstep}
	   </update>
	   
	   <insert id="insertReplyOne">
	     insert into board values (board_seq.nextval,#{id},#{btitle},#{bcontent},
	      sysdate,0,#{bgroup},#{bstep}+1,#{bindent}+1,#{bfile}) 
	   </insert>
	
	
	</mapper>
}
		<!DOCTYPE mapper
		    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
		    "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
		
		<mapper namespace="com.java.mapper.BoardMapper">
		   <!-- 게시글 전체개수 -->
		   <select id="selectListCount" resultType="int">
		       select count(*) from board
		       <if test="category == 'all'">
				   where btitle like '%'||#{s_word}||'%' or bcontent like '%'||#{s_word}||'%'
			   </if>
			   <if test="category == 'btitle'">
				   where btitle like '%'||#{s_word}||'%'
			   </if>
			   <if test="category == 'bcontent'">
				   where bcontent like '%'||#{s_word}||'%'
			   </if>
		       
		   </select>
		   
		   
		   <!-- 게시글 전체가져오기 -->
		   <select id="selectAll" resultType="com.java.dto.BoardDto">
		         select * from (select rownum rnum,a.* from 
			     (select * from board 
		        
				<if test="category == 'all'">
				     where btitle like '%'||#{s_word}||'%' or bcontent like '%'||#{s_word}||'%'
				</if>
				<if test="category == 'btitle'">
				     where btitle like '%'||#{s_word}||'%'
				</if>
				<if test="category == 'bcontent'">
				     where bcontent like '%'||#{s_word}||'%'
				</if>
				  
				<!-- 공통영역 -->
				   <![CDATA[
		           order by bgroup desc,bstep asc) a)
				   where rnum >= #{startRow} and rnum <= #{endRow}
				   ]]>
		   </select> 
		   
		   <!-- 게시글 1개가져오기 -->
		   <select id="selectOne" resultType="com.java.dto.BoardDto">
		      select * from board where bno=#{bno}
		   </select>
		
		   <!-- 게시글 이전글 -->
		   <select id="selectPrevOne" resultType="com.java.dto.BoardDto">
		      SELECT * FROM board WHERE bno=
		      (SELECT prevDto FROM (SELECT bno,lag(bno,1,-1) over(ORDER BY bgroup desc,bstep asc) as prevDto 
		      from board ORDER BY bgroup desc, bstep asc) where bno=#{bno})
		   </select>
		
		   <!-- 게시글 다음글 -->
		   <select id="selectNextOne" resultType="com.java.dto.BoardDto">
		      SELECT * FROM board WHERE bno=
		      (SELECT nextDto FROM (SELECT bno,lead(bno,1,-1) over(ORDER BY bgroup desc,bstep asc) as nextDto 
		      from board ORDER BY bgroup desc, bstep asc) where bno=#{bno})
		   </select>
		   
		   <!-- 게시글 1개저장 -->
		   <insert id="insertOne">
		      insert into board values (board_seq.nextval,#{id},#{btitle},#{bcontent},
		      sysdate,0,board_seq.currval,0,0,#{bfile})
		   </insert>
		   
		   <!-- 게시글 1개삭제 -->
		   <delete id="deleteOne">
		      delete board where bno=#{bno}
		   </delete>
		   
		   <!-- 게시글 1개수정 -->
		   <update id="updateOne">
		      update board set btitle=#{btitle},bcontent=#{bcontent},bfile=#{bfile} 
		      where bno=#{bno}
		   </update>
		   
		   <update id="updateBstepCount">
		      update board set bstep=bstep+1 where bgroup=#{bgroup} and bstep>#{bstep}
		   </update>
		   
		   <insert id="insertReplyOne">
		     insert into board values (board_seq.nextval,#{id},#{btitle},#{bcontent},
		      sysdate,0,#{bgroup},#{bstep}+1,#{bindent}+1,#{bfile}) 
		   </insert>
		
		
		</mapper>

