package test;

public class Solution {

	public void sum (int...nums ) {
		int sum = 0;
		for(int num : nums ) {
			sum += num;
		}
		System.out.println(sum);
	}
	
}
