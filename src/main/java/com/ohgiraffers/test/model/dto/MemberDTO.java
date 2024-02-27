package com.ohgiraffers.test.model.dto;

public class MemberDTO implements java.io.Serializable {
        private int memberCode;           // 사용자코드
        private String memberId;          // 아이디
        private String memberPw;          // 비밀번호
        private String memberName;        // 이름
        private String memberPhone;       // 전화번호
        private String memberEmail;       // 이메일
        private int memberType;           // 사용자 권한 구분

        public MemberDTO() {}

        public MemberDTO(int memberCode, String memberId, String memberPw, String memberName, String memberPhone, String memberEmail, int memberType) {
            this.memberCode = memberCode;
            this.memberId = memberId;
            this.memberPw = memberPw;
            this.memberName = memberName;
            this.memberPhone = memberPhone;
            this.memberEmail = memberEmail;
            this.memberType = memberType;
        }

        public int getMemberCode() {
            return memberCode;
        }

        public void setMemberCode(int memberCode) {
            this.memberCode = memberCode;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getMemberPw() {
            return memberPw;
        }

        public void setMemberPw(String memberPw) {
            this.memberPw = memberPw;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getMemberPhone() {
            return memberPhone;
        }

        public void setMemberPhone(String memberPhone) {
            this.memberPhone = memberPhone;
        }

        public String getMemberEmail() {
            return memberEmail;
        }

        public void setMemberEmail(String memberEmail) {
            this.memberEmail = memberEmail;
        }

        public int getMemberType() {
            return memberType;
        }

        public void setMemberType(int memberType) {
            this.memberType = memberType;
        }

        @Override
        public String toString() {
            String type = "";
            switch (memberType) {
                case 1: type = "관리자"; break;
                case 2: type = "회원"; break;
                case 3: type = "비회원"; break;
                case 4: type = "탈퇴회원"; break;
                case 5: type = "회원가입신청"; break;
            }
            return  "사용자 번호 : " + memberCode +
                    ", 아이디 : " + memberId + '\'' +
                    ", 비밀번호 : " + memberPw + '\'' +
                    ", 이름 : " + memberName + '\'' +
                    ", 전화번호 : " + memberPhone + '\'' +
                    ", 메일주소 : " + memberEmail + '\'' +
                    ", 회원 구분 : " + type;
        }
    }
