package com.blogboulder.BlogBoulderApi.models.mapper;

import com.blogboulder.BlogBoulderApi.models.dto.UserDto;
import com.blogboulder.BlogBoulderApi.models.entities.User;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements OrikaMapperFactoryConfigurer {

	public void configure(MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(User.class, UserDto.class)
				.mapNulls(false)
				.mapNullsInReverse(false)
//                .fieldAToB("id", "id")
				.fieldBToA("password", "password")
				.byDefault().register();
	}
}
