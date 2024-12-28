# HongikMentor

## 브랜치 규칙

### 1. 메인 브랜치(main)와 개발 브랜치(develop)
- `main` 및 `develop` 브랜치에 직접적으로 push가 불가능.
- 모든 커밋은 **pull request**를 통해서만 `main` 및 `develop` 브랜치로 merge 가능.
- **따라서**, 변경사항은 feature 브랜치를 생성하고 pull request를 이용하여 merg해야 함.

### 2. Pull Request 규칙
- 모든 Pull Request는 **팀원 전원**이 리뷰어로 지정되어야 함.
- Pull Request는 팀원의 **모든 승인(approve)**을 받아야만 merge가능.

### 목적
이 규칙은 코드의 품질을 유지하고, 팀원 간 코드 리뷰를 통해 협업의 일관성을 보장하기 위함.

---
# 컨벤션 - Prefix 구성

## 1.1 기능 개발 (Feature)
새로운 기능이나 개선 작업과 관련된 이슈:

- **[feature]** 새로운 기능 개발
- **[enhancement]** 기존 기능 개선
- **[ui]** 사용자 인터페이스 개선
- **[backend]** 백엔드 기능 추가

## 1.2 버그 관리 (Bug)
오류 수정이나 문제 해결과 관련된 이슈:

- **[bug]** 치명적 버그
- **[minor-bug]** 경미한 버그
- **[critical]** 긴급 수정 필요
- **[test]** 테스트 관련 오류

## 1.3 문서화 (Documentation)
문서 작성 및 수정과 관련된 이슈:

- **[docs]** 코드 문서화
- **[readme]** README 업데이트
- **[api-docs]** API 문서 추가

## 1.4 기술적 문제 (Tech/Refactor)
리팩토링 또는 기술적 논의와 관련된 이슈:

- **[refactor]** 코드 리팩토링
- **[tech]** 기술 스택 논의
- **[debt]** 기술 부채 제거

## 1.5 테스트 및 품질 관리 (Testing)
테스트 관련 작업:

- **[test]** 유닛 테스트 작성
- **[qa]** 품질 보증 테스트
- **[integration]** 통합 테스트 추가

## 1.6 프로젝트 관리 (Chore/Meta)
관리 작업 및 메타 이슈:

- **[chore]** 프로젝트 설정 작업
- **[meta]** 프로젝트 관련 논의
- **[dependencies]** 종속성 업데이트
