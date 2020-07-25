package com.tykon.api.framework.service.core.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tykon.api.framework.service.core.app.CustomAccessDeniedHandler;
import com.tykon.api.framework.service.core.security.JwtAuthenticationEntryPoint;
import com.tykon.api.framework.service.core.security.filter.JwtAuthenticationTokenFilter;
import com.tykon.api.framework.service.core.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private HelloParentPasswordEncoder passwordEncoder;

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationTokenFilter();
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// we don't need CSRF because our token is invulnerable
				.csrf().disable()

				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
				.accessDeniedHandler(new CustomAccessDeniedHandler()).and()

				// don't create session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

				// allow anonymous resource requests
				.antMatchers(HttpMethod.GET, "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js")
				.permitAll()

				.antMatchers("/auth/signin").permitAll().antMatchers("/auth/convertOldDotNetToken").permitAll()
				.antMatchers("user/signup").permitAll().antMatchers("/user/sendOTP").permitAll()
				.antMatchers("/user/verifyOTPAndLogin").permitAll().antMatchers("/user/verifyOTP").permitAll()
				.antMatchers("/user/test").permitAll().antMatchers("/user-auth/findByUserName").permitAll()
				.antMatchers("/user-auth/findByUserName").permitAll().antMatchers("/user-auth/findById/*").permitAll()
				.antMatchers("/user-auth/findByMobile/*").permitAll().antMatchers("/user/verifyEmail").permitAll()
				.antMatchers("/user/forgotPwd").permitAll().antMatchers("/user/initiateResetPwd").permitAll()
				.antMatchers("/user/resetPwd").permitAll().antMatchers("/user/fbsignup").permitAll()
				.antMatchers("/user/gpsignup").permitAll().antMatchers("/v2/api-docs/**").permitAll()
				.antMatchers("/configuration/ui").permitAll().antMatchers("/swagger-resources").permitAll()
				.antMatchers("/configuration/security").permitAll().antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/webjars/**").permitAll().antMatchers("/swagger-resources/**").permitAll()

				.antMatchers("/**/**/host_details").permitAll().antMatchers("/user/signup").permitAll()
				.antMatchers("/participation/reaction/get_comments").permitAll().antMatchers("/country/findAll")
				.permitAll()

				.antMatchers("/sfocre/sync").permitAll().antMatchers("/user-auth/fetchUserModel").permitAll()
				.antMatchers("/user/school_user_models").permitAll().antMatchers("/student/fetchStudentModelWithStdIds")
				.permitAll().antMatchers("/student/fetchBirthdayListOfStudent").permitAll()
				.antMatchers("/student/fetchStudentModelWithClassIds").permitAll()
				.antMatchers("/student/school_student_models").permitAll()
				.antMatchers("/user-auth/fetchUserModelBasedOnContactNo").permitAll()
				.antMatchers("/school/fetchClassModel").permitAll().antMatchers("/school/fetchGroupModel").permitAll()
				.antMatchers("/school/fetchClassModelBasedOnSchoolId").permitAll()
				.antMatchers("/notification/add_event").permitAll().antMatchers("/school/findByClassTeacherId/**")
				.permitAll().antMatchers("/school/findBySchoolClassIdsList").permitAll()
				.antMatchers("/student/findByStudentIdsList").permitAll().antMatchers("/user-auth/findByUserIdsList")
				.permitAll().antMatchers("/school/findSchoolClassesBySchoolId").permitAll()
				.antMatchers("/crud/student/find_by_school_and_class_id").permitAll()
				.antMatchers("/crud/group/find_by_school_and_student_id").permitAll()
				.antMatchers("/crud/group/find_by_schools_and_ids").permitAll()
				.antMatchers("/crud/student/find_by_school_id_and_class_ids").permitAll()
				.antMatchers("/crud/student/find_school_id_and_student_ids").permitAll()
				.antMatchers("/crud/student/find_by_school_id").permitAll()
				.antMatchers("/crud/student/find_by_school_id_and_student_id").permitAll()
				.antMatchers("/crud/student/find_by_school_id_and_parent_contact_numbers").permitAll()
				.antMatchers("/crud/user/find_by_id").permitAll()
				.antMatchers("/crud/user/find_by_school_id_and_user_ids").permitAll()
				.antMatchers("/crud/user/find_by_user_names").permitAll()
				.antMatchers("/crud/class/find_by_school_and_teacher_or_class_teacher_id").permitAll()
				.antMatchers("/crud/class/find_by_school_id").permitAll()
				.antMatchers("/crud/class/find_by_schools_and_id").permitAll()
				.antMatchers("/user-auth/find_by_school_id_and_user_ids").permitAll()
				.antMatchers("/school/find_by_schools_and_ids").permitAll()
				.antMatchers("/crud/user/find_users_by_school_id").permitAll().antMatchers("/event/add_event")
				.permitAll().antMatchers("/meeting/delete_meeting").permitAll()
				.antMatchers("/meeting/get_student_meeting").permitAll().antMatchers("/meeting/get_teacher_meeting")
				.permitAll().antMatchers("/meeting/start_teacher_meeting").permitAll()
				.antMatchers("/meeting/start_student_meeting").permitAll().antMatchers("/meeting/schedule_meeting")
				.permitAll().antMatchers("/meeting/get_participants").permitAll()
				.antMatchers("/meeting/add_zoom_properties").permitAll()
				.antMatchers("/crud/student/find_by_school_id_and_student_id").permitAll()
				.antMatchers("/meeting/update_school_users").permitAll().antMatchers("/crud/school/find_school_by_id")
				.permitAll().antMatchers("/meeting/generate_school_excel").permitAll()
				.antMatchers("/school/cache/clear/**").permitAll().antMatchers("/user/cache/clear/**").permitAll()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
				.anyRequest().authenticated();

		// Custom JWT based security filter
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

		// disable page caching
		httpSecurity.headers().cacheControl();

		// exclude options preflight from security
		httpSecurity.cors();
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		auth.authenticationProvider(provider);

		// customize authentication component for our table by providing the queries for
		// selecting user and role from our database
		/*
		 * auth.jdbcAuthentication(). dataSource(this.dataSource).
		 * .usersByUsernameQuery("select user_name,password_hash,1 as is_active from up_user where user_name=?"
		 * ) // provided the query for selecting user from our database using user name
		 * of login form //.
		 * authoritiesByUsernameQuery("select u1.emailid, u2.name from users u1, roles u2 where u1.roles_id = u2.id and u1.emailid=?"
		 * ); //provided query to get role
		 * .authoritiesByUsernameQuery("select u1.user_name, u1.user_name as role from up_user u1 where u1.user_name=?"
		 * ); //provided query to get role
		 */
		// passwordEncoder(shaPasswordEncoder);

	}

}