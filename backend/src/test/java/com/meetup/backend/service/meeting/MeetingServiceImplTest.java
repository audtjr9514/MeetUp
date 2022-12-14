package com.meetup.backend.service.meeting;

import com.meetup.backend.dto.schedule.meeting.MeetingRequestDto;
import com.meetup.backend.dto.schedule.meeting.MeetingResponseDto;
import com.meetup.backend.dto.schedule.meeting.MeetingUpdateRequestDto;
import com.meetup.backend.entity.channel.Channel;
import com.meetup.backend.entity.channel.ChannelType;
import com.meetup.backend.entity.channel.ChannelUser;
import com.meetup.backend.entity.meetup.Meetup;
import com.meetup.backend.entity.team.Team;
import com.meetup.backend.entity.team.TeamType;
import com.meetup.backend.entity.team.TeamUser;
import com.meetup.backend.entity.user.RoleType;
import com.meetup.backend.entity.user.User;
import com.meetup.backend.exception.ApiException;
import com.meetup.backend.repository.channel.ChannelRepository;
import com.meetup.backend.repository.channel.ChannelUserRepository;
import com.meetup.backend.repository.meetup.MeetupRepository;
import com.meetup.backend.repository.schedule.MeetingRepository;
import com.meetup.backend.repository.team.TeamRepository;
import com.meetup.backend.repository.team.TeamUserRepository;
import com.meetup.backend.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Slf4j
class MeetingServiceImplTest {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private TeamRepository teamRepository;
//    @Autowired
//    private TeamUserRepository teamUserRepository;
//    @Autowired
//    private ChannelRepository channelRepository;
//    @Autowired
//    private ChannelUserRepository channelUserRepository;
//
//    @Autowired
//    private MeetupRepository meetupRepository;
//
//    @Autowired
//    private MeetingRepository meetingRepository;
//
//    @Autowired
//    private MeetingService meetingService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    private Meetup consultantMeetup;
//    private Meetup coachMeetup;
//
//    @BeforeEach
//    void before() {
//        User con1 = userRepository.save(User.builder()
//                .id("consultant")
//                .nickname("?????????")
//                .password(passwordEncoder.encode("password123"))
//                .firstLogin(true)
//                .role(RoleType.ROLE_Consultant)
//                .build());
//
//        User user1 = userRepository.save(User.builder()
//                .id("user1")
//                .nickname("?????????")
//                .password(passwordEncoder.encode("password123"))
//                .firstLogin(true)
//                .role(RoleType.ROLE_Student)
//                .build());
//        User user2 = userRepository.save(User.builder()
//                .id("user2")
//                .nickname("?????????")
//                .password(passwordEncoder.encode("password123"))
//                .firstLogin(true)
//                .role(RoleType.ROLE_Student)
//                .build());
//        User coach1 = userRepository.save(User.builder()
//                .id("coach1")
//                .nickname("?????????")
//                .password(passwordEncoder.encode("password123"))
//                .firstLogin(true)
//                .role(RoleType.ROLE_Coach)
//                .build());
//
//
//        Team team = teamRepository.save(Team.builder()
//                .id("team1")
//                .name("1team")
//                .displayName("1???")
//                .type(TeamType.Open)
//                .build());
//
//        teamUserRepository.save(new TeamUser(team, con1));
//        teamUserRepository.save(new TeamUser(team, user1));
//        teamUserRepository.save(new TeamUser(team, user2));
//        teamUserRepository.save(new TeamUser(team, coach1));
//
//        Channel channel1 = channelRepository.save(Channel.builder()
//                .id("channel1")
//                .name("1channel")
//                .displyName("??????1")
//                .team(team)
//                .type(ChannelType.Open)
//                .build());
//        Channel channel2 = channelRepository.save(Channel.builder()
//                .id("channel2")
//                .name("1channe2")
//                .displyName("??????2")
//                .team(team)
//                .type(ChannelType.Open)
//                .build());
//
//        // con1, user1??? channel 1, 2 ?????? ??????
//        // user2??? channel1?????? ??????.
//        channelUserRepository.save(new ChannelUser(con1, channel1));
//        channelUserRepository.save(new ChannelUser(con1, channel2));
//        channelUserRepository.save(new ChannelUser(user1, channel1));
//        channelUserRepository.save(new ChannelUser(user2, channel1));
//        channelUserRepository.save(new ChannelUser(user1, channel2));
//        channelUserRepository.save(new ChannelUser(coach1, channel1));
//        channelUserRepository.save(new ChannelUser(coach1, channel2));
//
//        consultantMeetup = meetupRepository.save(new Meetup("???????????? ?????? ??????", "AAAAA", con1, channel1));
//        coachMeetup = meetupRepository.save(new Meetup("?????? ?????? ??????", "BBBBB", coach1, channel2));
//
//
//    }
//
//    @AfterEach
//    void after() {
//        meetingRepository.deleteAll();
//        meetupRepository.deleteAll();
//        channelUserRepository.deleteAll();
//        channelRepository.deleteAll();
//        teamUserRepository.deleteAll();
//        teamRepository.deleteAll();
//        userRepository.deleteAll();
//    }
//
//    @Test
//    @DisplayName("?????? ???????????? ?????? ?????? ??????")
//    void getMeetingResponseDtoById() {
//        User user1 = userRepository.findById("user1").get();
//        User user2 = userRepository.findById("user2").get();
//        Long meetingId1 = meetingService.createMeeting(user1.getId(), MeetingRequestDto
//                .builder()
//                .start("2022-11-04 10:00:00")
//                .end("2022-11-04 11:30:00")
//                .title("??????1")
//                .content("??????1")
//                .meetupId(consultantMeetup.getId())
//                .build());
//        Long meetingId2 = meetingService.createMeeting(user2.getId(), MeetingRequestDto
//                .builder()
//                .start("2022-11-04 16:00:00")
//                .end("2022-11-04 16:30:00")
//                .title("??????2")
//                .content("??????2")
//                .meetupId(consultantMeetup.getId())
//                .build());
//        MeetingResponseDto meetingResponseDto1 = meetingService.getMeetingDetail(user1.getId(), meetingId1);
//        assertThat(meetingResponseDto1.getContent()).isEqualTo("??????1");
//
//        MeetingResponseDto meetingResponseDto2 = meetingService.getMeetingDetail(consultantMeetup.getManager().getId(), meetingId1);
//        assertThat(meetingResponseDto2.getContent()).isEqualTo("??????1");
//
//        assertThatThrownBy(() -> {
//            meetingService.getMeetingDetail(user2.getId(), meetingId1);
//        }).isInstanceOf(ApiException.class).hasMessageContaining("??????");
//
//    }
//
//
//    @Test
//    @DisplayName("?????? ??????")
//    void createMeeting() {
//        User user1 = userRepository.findById("user1").get();
//        User user2 = userRepository.findById("user2").get();
//        Long meetingId1 = meetingService.createMeeting(user1.getId(), MeetingRequestDto
//                .builder()
//                .start("2022-11-04 10:00:00")
//                .end("2022-11-04 11:30:00")
//                .title("??????1")
//                .content("??????1")
//                .meetupId(consultantMeetup.getId())
//                .build());
//        Long meetingId2 = meetingService.createMeeting(user2.getId(), MeetingRequestDto
//                .builder()
//                .start("2022-11-04 16:00:00")
//                .end("2022-11-04 16:30:00")
//                .title("??????2")
//                .content("??????2")
//                .meetupId(consultantMeetup.getId())
//                .build());
//        MeetingResponseDto meetingResponseDto1 = meetingService.getMeetingDetail(user1.getId(), meetingId1);
//        assertThat(meetingResponseDto1.getContent()).isEqualTo("??????1");
//
//        MeetingResponseDto meetingResponseDto2 = meetingService.getMeetingDetail(consultantMeetup.getManager().getId(), meetingId1);
//        assertThat(meetingResponseDto2.getContent()).isEqualTo("??????1");
//
//        MeetingResponseDto meetingResponseDto3 = meetingService.getMeetingDetail(user2.getId(), meetingId2);
//        assertThat(meetingResponseDto3.getContent()).isEqualTo("??????2");
//
//    }
//
//    @Test
//    @DisplayName("?????? ?????? ???????????? ?????? ??????")
//    void refuseCreateMeeting() {
//        User user1 = userRepository.findById("user1").get();
//        User user2 = userRepository.findById("user2").get();
//        Long meetingId1 = meetingService.createMeeting(user1.getId(), MeetingRequestDto
//                .builder()
//                .start("2022-11-04 10:00:00")
//                .end("2022-11-04 11:30:00")
//                .title("??????1")
//                .content("??????1")
//                .meetupId(consultantMeetup.getId())
//                .build());
//        Long meetingId2 = meetingService.createMeeting(user1.getId(), MeetingRequestDto
//                .builder()
//                .start("2022-11-04 14:00:00")
//                .end("2022-11-04 14:30:00")
//                .title("??????1")
//                .content("??????1")
//                .meetupId(consultantMeetup.getId())
//                .build());
//
//        assertThatThrownBy(() -> {
//            Long meetingId3 = meetingService.createMeeting(user2.getId(), MeetingRequestDto
//                    .builder()
//                    .start("2022-11-04 13:50:00")
//                    .end("2022-11-04 14:20:00")
//                    .title("??????3")
//                    .content("??????3")
//                    .meetupId(consultantMeetup.getId())
//                    .build());
//        }).isInstanceOf(ApiException.class).hasMessageContaining("??????");
//
//    }
//
//    @Test
//    @DisplayName("?????? ??????")
//    void updateMeeting() {
//        User user1 = userRepository.findById("user1").get();
//        Long meetingId1 = meetingService.createMeeting(user1.getId(), MeetingRequestDto
//                .builder()
//                .start("2022-11-04 10:00:00")
//                .end("2022-11-04 11:30:00")
//                .title("??????1")
//                .content("??????1")
//                .meetupId(consultantMeetup.getId())
//                .build());
//        Long meetingId2 = meetingService.updateMeeting(user1.getId(), MeetingUpdateRequestDto
//                .builder()
//                .id(meetingId1)
//                .start("2022-11-04 14:00:00")
//                .end("2022-11-04 14:30:00")
//                .title("??????2")
//                .content("?????? 22").
//                build());
//        MeetingResponseDto meetingResponseDto = meetingService.getMeetingDetail(user1.getId(), meetingId2);
//        assertThat(meetingResponseDto.getContent()).isEqualTo("?????? 22");
//    }
//
//    @Test
//    @DisplayName("?????? ?????? ???????????? ?????? ??????")
//    void refuseUpdateMeeting() {
//        User user1 = userRepository.findById("user1").get();
//        User user2 = userRepository.findById("user2").get();
//        Long meetingId1 = meetingService.createMeeting(user1.getId(), MeetingRequestDto
//                .builder()
//                .start("2022-11-04 10:00:00")
//                .end("2022-11-04 11:30:00")
//                .title("??????1")
//                .content("??????1")
//                .meetupId(consultantMeetup.getId())
//                .build());
//        Long meetingId2 = meetingService.createMeeting(user1.getId(), MeetingRequestDto
//                .builder()
//                .start("2022-11-04 14:00:00")
//                .end("2022-11-04 14:30:00")
//                .title("??????2")
//                .content("??????2")
//                .meetupId(consultantMeetup.getId())
//                .build());
//
//        assertThatThrownBy(() -> {
//            Long meetingId3 = meetingService.updateMeeting(user1.getId(), MeetingUpdateRequestDto
//                    .builder()
//                    .id(meetingId2)
//                    .start("2022-11-04 09:00:00")
//                    .end("2022-11-04 11:00:00")
//                    .title("??????2")
//                    .content("?????? 22").
//                    build());
//        }).isInstanceOf(ApiException.class).hasMessageContaining("??????");
//    }
//
//    @Test
//    @DisplayName("?????? ??????")
//    void deleteMeeting() {
//        User user1 = userRepository.findById("user1").get();
//        Long meetingId1 = meetingService.createMeeting(user1.getId(), MeetingRequestDto
//                .builder()
//                .start("2022-11-04 10:00:00")
//                .end("2022-11-04 11:30:00")
//                .title("??????1")
//                .content("??????1")
//                .meetupId(consultantMeetup.getId())
//                .build());
//        Long meetingId2 = meetingService.createMeeting(user1.getId(), MeetingRequestDto
//                .builder()
//                .start("2022-11-04 14:00:00")
//                .end("2022-11-04 14:30:00")
//                .title("??????2")
//                .content("??????2")
//                .meetupId(consultantMeetup.getId())
//                .build());
//        meetingService.deleteMeeting(user1.getId(), meetingId2);
//
//        assertThatThrownBy(() -> {
//            meetingService.getMeetingDetail(user1.getId(), meetingId2);
//        }).isInstanceOf(ApiException.class).hasMessageContaining("??????");
//    }
}