package resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.Assert;
import org.testng.annotations.Test;

public class JavaPractise {
	@Test(enabled=false)
	public void streamTest()
	{
		List<String> lt = new ArrayList<String>();
		lt.add("adam");
		lt.add("adut");
		lt.add("lim");
		lt.add("tod");
		lt.add("advi");
		lt.add("adam");
		
		long l = lt.stream().filter(a->a.startsWith("a")).count();
		
		System.out.println(l);
		
		Set<String> s = new HashSet<String>();
		
		lt.forEach(a->System.out.println(a));
		
		lt.forEach(a->s.add(a));
		
		s.forEach(a->System.out.println(a));
	}
	
	@Test(enabled=false)
	public void streamSort()
	{
		Stream.of("boss","ant","odd","aut","anthe").map(s->s.toUpperCase()).sorted();
		
		List<String> lt1 = new ArrayList<String>();
		lt1.add("adam");
		lt1.add("adut");
		lt1.add("lim");
		lt1.add("tod");
		lt1.add("advi");
		lt1.add("adam");
		
		List<String> st = Arrays.asList("fun","fat","bit","add");
		
		Stream<String> ct = Stream.concat(lt1.stream(), st.stream());
		
		//ct.forEach(s->System.out.println(s));
		
		boolean flag = ct.anyMatch(s->s.equalsIgnoreCase("jhkjk"));
		
		Assert.assertTrue(flag);
		
	}
	
	@Test
	public void uniqueNumber()
	{
		List<Integer> num = Arrays.asList(2,43,12,45,3,2,12,1,42,9);
		
		List<Integer> n = num.stream().sorted().distinct().collect(Collectors.toList());
		
		n.forEach(s->System.out.println(s));
	}
}
