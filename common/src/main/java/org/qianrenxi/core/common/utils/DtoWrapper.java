package org.qianrenxi.core.common.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import io.swagger.annotations.ApiModelProperty;

public class DtoWrapper<T> implements Serializable {

	private static final long serialVersionUID = 8992436576262574064L;

	private T result;

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	/**
	 * 过滤字段：指定需要序列化的字段
	 */
	private transient Map<Class<?>, Set<String>> includes;

	/**
	 * 过滤字段：指定不需要序列化的字段
	 */
	private transient Map<Class<?>, Set<String>> excludes;

	public DtoWrapper() {
	}

	public DtoWrapper<T> data(T data) {
		this.result = data;
		return this;
	}

	public static <T> DtoWrapper<T> wrapper(T data) {
		return new DtoWrapper<T>().data(data);
	}

	public String toJSONString() {
		return JSON.toJSONString(
				this.getResult(), this.filters(),
				SerializerFeature.PrettyFormat,
				SerializerFeature.WriteMapNullValue
			);
	}

	public DtoWrapper<T> include(Class<?> type, String... fields) {
		return include(type, Arrays.asList(fields));
	}

	public DtoWrapper<T> include(Class<?> type, Collection<String> fields) {
		if (includes == null)
			includes = new HashMap<>();
		if (fields == null || fields.isEmpty())
			return this;
		fields.forEach(field -> {
			if (field.contains(".")) {
				String tmp[] = field.split("[.]", 2);
				try {
					Field field1 = type.getDeclaredField(tmp[0]);
					if (field1 != null) {
						include(field1.getType(), tmp[1]);
					}
				} catch (Throwable e) {
				}
			} else {
				getStringListFromMap(includes, type).add(field);
			}
		});
		return this;
	}

	public DtoWrapper<T> exclude(Class<?> type, Collection<String> fields) {
		if (excludes == null)
			excludes = new HashMap<>();
		if (fields == null || fields.isEmpty())
			return this;
		fields.forEach(field -> {
			if (field.contains(".")) {
				String tmp[] = field.split("[.]", 2);
				try {
					Field field1 = type.getDeclaredField(tmp[0]);
					if (field1 != null) {
						exclude(field1.getType(), tmp[1]);
					}
				} catch (Throwable e) {
				}
			} else {
				getStringListFromMap(excludes, type).add(field);
			}
		});
		return this;
	}

	public DtoWrapper<T> exclude(Collection<String> fields) {
		if (excludes == null)
			excludes = new HashMap<>();
		if (fields == null || fields.isEmpty())
			return this;
		Class<? extends Object> type;
		if (getResult() != null)
			type = getResult().getClass();
		else
			return this;
		exclude(type, fields);
		return this;
	}

	public DtoWrapper<T> include(Collection<String> fields) {
		if (includes == null)
			includes = new HashMap<>();
		if (fields == null || fields.isEmpty())
			return this;
		Class<? extends Object> type;
		if (getResult() != null)
			type = getResult().getClass();
		else
			return this;
		include(type, fields);
		return this;
	}

	public DtoWrapper<T> exclude(Class<? extends Object> type, String... fields) {
		return exclude(type, Arrays.asList(fields));
	}

	public DtoWrapper<T> exclude(String... fields) {
		return exclude(Arrays.asList(fields));
	}

	public DtoWrapper<T> include(String... fields) {
		return include(Arrays.asList(fields));
	}

	protected Set<String> getStringListFromMap(Map<Class<?>, Set<String>> map, Class<?> type) {
		return map.computeIfAbsent(type, k -> new HashSet<>());
	}

	@Override
	public String toString() {
		return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss");
	}

	@ApiModelProperty(hidden = true)
	public Map<Class<?>, Set<String>> getExcludes() {
		return excludes;
	}

	@ApiModelProperty(hidden = true)
	public Map<Class<?>, Set<String>> getIncludes() {
		return includes;
	}
	
	private SerializeFilter[] filters() {
		List<SerializeFilter> filters = new ArrayList<>();
		if (this.getIncludes() != null)
			for (Map.Entry<Class<?>, Set<String>> classSetEntry : this.getIncludes().entrySet()) {
				SimplePropertyPreFilter filter = new SimplePropertyPreFilter(classSetEntry.getKey());
				filter.getIncludes().addAll(classSetEntry.getValue());
				filters.add(filter);
			}
		if (this.getExcludes() != null)
			for (Map.Entry<Class<?>, Set<String>> classSetEntry : this.getExcludes().entrySet()) {
				SimplePropertyPreFilter filter = new SimplePropertyPreFilter(classSetEntry.getKey());
				filter.getExcludes().addAll(classSetEntry.getValue());
				filters.add(filter);
			}
		//PropertyFilter responseMessageFilter = (object, name, value) -> !(object instanceof ResponseMessage) || value != null;

		PropertyFilter responseMessageFilter = (object, name, value) ->  value != null;
		filters.add(responseMessageFilter);
		return filters.toArray(new SerializeFilter[filters.size()]);
	}

}
