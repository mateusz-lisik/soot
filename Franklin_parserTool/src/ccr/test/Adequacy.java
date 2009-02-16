package ccr.test;

import ccr.app.Application;
import ccr.stat.*;

import java.io.*;
import java.util.*;
import java.math.*;

public class Adequacy {
	//2009/2/15:
	public static HashMap testCases= new HashMap();
	
	public static TestSet[] getTestSets(
			String appClassName, Criterion c, TestSet testpool, int maxTrials, int num) {		
		TestSet testSets[] = new TestSet[num];
		for (int i = 0; i < num; i++) {
			testSets[i] = getAdequacyTestSet(appClassName, c, testpool, maxTrials);
			System.out.println("Test set " + i + ": " + testSets[i].toString());
		}			
		return testSets;
	}
	
	public static void getTestSets(
			String appClassName, Criterion c, TestSet testpool, 
			int maxTrials, int num, String filename) {
		
		
		
		TestSet testSets[] = getTestSets(appClassName, c, testpool, maxTrials, num);
		try {
			
			//the Size of test set: max, min, average, std
			int maxSize = 0;
			int minSize = Integer.MAX_VALUE;
			int totalSize = 0;			
			int averageSize = 0;
			double stdSize = 0.0;
			
			//the Coverage of test set:
			double maxCoverage = 0.0;
			double minCoverage = Double.MAX_VALUE;
			double totalCoverage = 0.0;
			double averageCoverage = 0.0;
			double stdCoverage = 0.0;
			
			//the time of test set:
			long maxTime = 0;
			long minTime = Long.MAX_VALUE;
			long totalTime = 0;
			long averageTime = 0;
			double stdTime = 0;
						
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			for (int i = 0; i < testSets.length; i++) {				
				bw.write(i + "\t" + testSets[i].toString());
				bw.flush();
				bw.newLine();
				
				//size
				int currentSize = testSets[i].size();
				if(maxSize < currentSize)
					maxSize = currentSize;
				if(minSize > currentSize)
					minSize = currentSize;
				totalSize += currentSize;
				
				//coverage
				double currentCoverage = testSets[i].getCoverage();
				if(maxCoverage < currentCoverage)
					maxCoverage = currentCoverage;
				if(minCoverage > currentCoverage)
					minCoverage = currentCoverage;
				totalCoverage += currentCoverage;
				
				//time
				long currentTime = testSets[i].geneTime;
				if(maxTime < currentTime)
					maxTime = currentTime;
				if(minTime > currentTime)
					minTime = currentTime;
				totalTime += currentTime; 							
			}		
			
			//Size, Coverage, time 
			averageSize = totalSize / testSets.length;
			averageCoverage = totalCoverage/testSets.length;
			averageTime = totalTime/testSets.length;
			
			double tempSize = 0.0;
			double tempCoverage = 0.0;
			double tempTime = 0.0;
			for (int i = 0; i < testSets.length; i++) {
				tempSize += (testSets[i].size() - averageSize) * (testSets[i].size() - averageSize);
				tempCoverage +=(testSets[i].getCoverage() - averageCoverage)*(testSets[i].getCoverage() - averageCoverage);
				tempTime += (testSets[i].geneTime - averageTime)*(testSets[i].geneTime - averageTime);
			}
			stdSize = Math.sqrt(tempSize/testSets.length);
			stdCoverage = Math.sqrt(tempCoverage/testSets.length);
			stdTime = Math.sqrt(tempTime/testSets.length);
			
			String line = 
					"maxSize:" + maxSize + "\t" + "minSize:" + minSize + "\t" + "averageSize:" + 
					averageSize + "\t" + "stdSize:" + stdSize + 
					"\n" + "maxCoverage:" + maxCoverage + "\t" + "minCoverage:" + minCoverage + "\t" + "averageCoverage:" + 
					averageCoverage + "\t" + "stdCoverage:" + stdCoverage +
					"\n" + "maxTime:" + maxTime + "\t" + "minTime:" + minTime + "\t" + "averageTime:" + 
					averageTime + "\t" + "stdTime:" + stdTime;
			
			bw.write(line);
			bw.flush();			
			bw.close();
			System.out.println("Test sets " + filename + " generated");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	
	public static TestSet[] getTestSets(
			String appClassName, Criterion c, TestSet testpool, int maxTrials, int num, int testSuiteSize) {		
		TestSet testSets[] = new TestSet[num];
		for (int i = 0; i < num; i++) {
			testSets[i] = getAdequacyTestSet(appClassName, c, testpool, maxTrials, testSuiteSize);
			System.out.println("Test set " + i + ": " + testSets[i].toString());
		}			
		return testSets;
	}
	
	//2009/2/15:
	public static void getTestSets(
			String appClassName, Criterion c, TestSet testpool, 
			int maxTrials, int num, String filename, int testSuiteSize) {
		
		
		
		TestSet testSets[] = getTestSets(appClassName, c, testpool, maxTrials, num, testSuiteSize);
		try {
			
			//the Size of test set: max, min, average, std
			int maxSize = 0;
			int minSize = Integer.MAX_VALUE;
			int totalSize = 0;			
			int averageSize = 0;
			double stdSize = 0.0;
			
			//the Coverage of test set:
			double maxCoverage = 0.0;
			double minCoverage = Double.MAX_VALUE;
			double totalCoverage = 0.0;
			double averageCoverage = 0.0;
			double stdCoverage = 0.0;
			
			//the time of test set:
			long maxTime = 0;
			long minTime = Long.MAX_VALUE;
			long totalTime = 0;
			long averageTime = 0;
			double stdTime = 0;
						
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			for (int i = 0; i < testSets.length; i++) {				
				bw.write(testSets[i].toString());
				bw.flush();
				bw.newLine();
				
				//size
				int currentSize = testSets[i].size();
				if(maxSize < currentSize)
					maxSize = currentSize;
				if(minSize > currentSize)
					minSize = currentSize;
				totalSize += currentSize;
				
				//coverage
				double currentCoverage = testSets[i].getCoverage();
				if(maxCoverage < currentCoverage)
					maxCoverage = currentCoverage;
				if(minCoverage > currentCoverage)
					minCoverage = currentCoverage;
				totalCoverage += currentCoverage;
				
				//time
				long currentTime = testSets[i].geneTime;
				if(maxTime < currentTime)
					maxTime = currentTime;
				if(minTime > currentTime)
					minTime = currentTime;
				totalTime += currentTime; 							
			}		
			
			//Size, Coverage, time 
			averageSize = totalSize / testSets.length;
			averageCoverage = totalCoverage/testSets.length;
			averageTime = totalTime/testSets.length;
			
			double tempSize = 0.0;
			double tempCoverage = 0.0;
			double tempTime = 0.0;
			for (int i = 0; i < testSets.length; i++) {
				tempSize += (testSets[i].size() - averageSize) * (testSets[i].size() - averageSize);
				tempCoverage +=(testSets[i].getCoverage() - averageCoverage)*(testSets[i].getCoverage() - averageCoverage);
				tempTime += (testSets[i].geneTime - averageTime)*(testSets[i].geneTime - averageTime);
			}
			stdSize = Math.sqrt(tempSize/testSets.length);
			stdCoverage = Math.sqrt(tempCoverage/testSets.length);
			stdTime = Math.sqrt(tempTime/testSets.length);
			
			String line = 
					"maxSize:" + maxSize + "\t" + "minSize:" + minSize + "\t" + "averageSize:" + 
					averageSize + "\t" + "stdSize:" + stdSize + 
					"\n" + "maxCoverage:" + maxCoverage + "\t" + "minCoverage:" + minCoverage + "\t" + "averageCoverage:" + 
					averageCoverage + "\t" + "stdCoverage:" + stdCoverage +
					"\n" + "maxTime:" + maxTime + "\t" + "minTime:" + minTime + "\t" + "averageTime:" + 
					averageTime + "\t" + "stdTime:" + stdTime;
			
			bw.write(line);
			bw.flush();			
			bw.close();
			System.out.println("Test sets " + filename + " generated");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static TestSet[] getTestSets(String filename) {
		
		Vector lines = new Vector();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			while (line != null) {
				lines.add(line);
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}		
	//	TestSet testSets[] = new TestSet[lines.size()];
		
		//1/16/2008:Martin: the last three lines are not TestSets
		TestSet testSets[] = new TestSet[lines.size() - 3];
		for (int i = 0; i < lines.size() - 3; i++) {
			testSets[i] = new TestSet((String) lines.get(i));
		}
		return testSets;
	}
	
	
	//2008/8/19:modified version
	public static TestSet getAdequacyTestSet(
			String appClassName, Criterion c, TestSet testpool, int maxTrials, int testSuiteSize) {
		
//		Criterion criterion = (Criterion) c.clone();
//		TestSet testSet = new TestSet();
//		TestSet visited = new TestSet();
//		int originalSize = criterion.size();
		
		//2009-2-15:
		Criterion criterion;
		TestSet testSet;
		TestSet visited;
		int originalSize;
		
		//1/15/2008
		long time = System.currentTimeMillis();
		
		//version 1: to see the average test suite size to satisfy a specified criteria
//		while (visited.size() < maxTrials && visited.size() < testpool.size() && 
//				criterion.size() > 0 ) {
//			String testcase = testpool.getByRandom();
//			if (!visited.contains(testcase)) {
//				visited.add(testcase);
//				String stringTrace[] = TestDriver.getTrace(appClassName, testcase);
//										
//				if (checkCoverage(stringTrace, criterion)) {
//					testSet.add(testcase);
//				}
//			}
//		}
//		int currentSize = criterion.size();
//		testSet.setCoverage((float) (originalSize - currentSize) / (float) originalSize);			
//	
		
		
		//version 2: to see the coverage when the test suite size has been fixed
//		boolean flag = false;
//		while (visited.size() < maxTrials && visited.size() < testpool.size()  
//				 && testSet.size() < testSuiteSize) {
//			String testcase = testpool.getByRandom();
//			if (!visited.contains(testcase)) {
//				visited.add(testcase);
//				String stringTrace[] = TestDriver.getTrace(appClassName, testcase);
//				
//				if(criterion.size()==0){
//					criterion = (Criterion) c.clone();
//					testSet.setCoverage(1.0);
//					flag = true;
//				}	
//				
//				if (checkCoverage(stringTrace, criterion)) {
//					testSet.add(testcase);
//				}
//			}
//		}  
//		
//		if(!flag){
//			int currentSize = criterion.size();
//			testSet.setCoverage((float) (originalSize - currentSize) / (float) originalSize);			
//		}
		
		
		//version 3: to see the coverage criteria given a criteria and the minimum test suite size
//		boolean satisfy = false;
//		while (visited.size() < maxTrials && visited.size() < testpool.size()  
//				 && (!satisfy ||testSet.size() < testSuiteSize)) {
//			String testcase = testpool.getByRandom();
//			if (!visited.contains(testcase)) {
//				visited.add(testcase);
//				String stringTrace[] = TestDriver.getTrace(appClassName, testcase);
//				
//				if(criterion.size()==0){
//					criterion = (Criterion) c.clone();
//					testSet.setCoverage(1.0);
//					satisfy = true;
//				}	
//				
//				if (checkCoverage(stringTrace, criterion)) {
//					testSet.add(testcase);
//				}
//			}
//		}  
//		
//		if(!satisfy){
//			int currentSize = criterion.size();
//			testSet.setCoverage((float) (originalSize - currentSize) / (float) originalSize);			
//		}
		
		//version4: when a criterion is covered but the required test set has not been met, then add random test cases to this set
//		while (visited.size() < maxTrials && visited.size() < testpool.size() && 
//				criterion.size() > 0 ) {
//			String testcase = testpool.getByRandom();
//			if (!visited.contains(testcase)) {
//				visited.add(testcase);
//				String stringTrace[] = TestDriver.getTrace(appClassName, testcase);
//										
//				if (checkCoverage(stringTrace, criterion)) {
//					testSet.add(testcase);
//				}
//			}
//		}
//		int currentSize = criterion.size();
//		testSet.setCoverage((float) (originalSize - currentSize) / (float) originalSize);
//		if(criterion.size() == 0){ // when criteria has been met, but the test set size is too small, then add random test cases to this set
//			while(testSet.size() < testSuiteSize){
//				String testcase = testpool.getByRandom();
//				if (!visited.contains(testcase)) {
//					visited.add(testcase);
//					testSet.add(testcase);
//				}					
//		    }	
//		}
	    
		//2009/2/15:
		//version 5: get adequate test sets with a specified size
		do{
			criterion = (Criterion) c.clone();
			testSet = new TestSet();
			visited = new TestSet();
			originalSize = criterion.size();
			while (visited.size() < maxTrials && visited.size() < testpool.size() && 
					criterion.size() > 0 ) {
				String testcase = testpool.getByRandom();
				if (!visited.contains(testcase)) {
					visited.add(testcase);
					String stringTrace[] = TestDriver.getTrace(appClassName, testcase);
											
					if (checkCoverage(stringTrace, criterion)) {
						testSet.add(testcase);
					}
				}
			}	
		}while(testSet.size()!= testSuiteSize);
		
		
		
		int currentSize = criterion.size();
		testSet.setCoverage((float) (originalSize - currentSize) / (float) originalSize);			
	
		
		
		testSet.geneTime = System.currentTimeMillis() - time;
	//	System.out.println(c);
	//	System.out.println(criterion);
		return testSet;
	}
	
	
	public static TestSet getAdequacyTestSet(
			String appClassName, Criterion c, TestSet testpool, int maxTrials) {
		
		Criterion criterion = (Criterion) c.clone();
		TestSet testSet = new TestSet();
		TestSet visited = new TestSet();
		int originalSize = criterion.size();
		
		//1/15/2008
		long time = System.currentTimeMillis();
		
		//version 1: to see the average test suite size to satisfy a specified criteria
		while (visited.size() < maxTrials && visited.size() < testpool.size() && 
				criterion.size() > 0 ) {
			String testcase = testpool.getByRandom();
			if (!visited.contains(testcase)) {
				visited.add(testcase);
				String stringTrace[] = TestDriver.getTrace(appClassName, testcase);
										
				if (checkCoverage(stringTrace, criterion)) {
					testSet.add(testcase);
				}
			}
		}
		int currentSize = criterion.size();
		testSet.setCoverage((float) (originalSize - currentSize) / (float) originalSize);			
	
		
		
		
		//version 2: to see the average test suite size to satisfy a specified criteria while the size of test set should less than 19
		/*boolean flag = false;
		while (visited.size() < maxTrials && visited.size() < testpool.size()  
				 && testSet.size() < 19) {
			String testcase = testpool.getByRandom();
			if (!visited.contains(testcase)) {
				visited.add(testcase);
				String stringTrace[] = TestDriver.getTrace(appClassName, testcase);
				
				if(criterion.size()==0){
					criterion = (Criterion) c.clone();
					testSet.setCoverage(1.0);
					flag = true;
				}	
				
				if (checkCoverage(stringTrace, criterion)) {
					testSet.add(testcase);
				}
			}
		}  
		
		if(!flag){
			int currentSize = criterion.size();
			testSet.setCoverage((float) (originalSize - currentSize) / (float) originalSize);			
		}*/
		
		
		testSet.geneTime = System.currentTimeMillis() - time;
	//	System.out.println(c);
	//	System.out.println(criterion);
		return testSet;
	}
	
	private static boolean checkCoverage(String stringTrace[], Criterion criterion) {
		
		Node trace[] = new Node[stringTrace.length];
		for (int i = 0; i < trace.length; i++) {
			trace[i] = NodeIndex.getInstance().get(stringTrace[i]);
		}
		boolean effective = false;
				
		for (int i = 0; i < trace.length; i++) {
			if (criterion.remove(trace[i])) {
				effective = true;
			}
			if (trace[i] instanceof PolicyNode) {
				if (criterion.remove(((PolicyNode) trace[i]).policy)) {
					effective = true;
				}
			}
			if (criterion.containsDefinition(trace[i])) {
				for (int j = i + 1; j < trace.length; j++) {
					if (criterion.remove(trace[i], trace[j])) {
						effective = true;
					}
					if (trace[j] != null && trace[i].hasSameDef(trace[j])) {
						break;
					}
				}
			}
		}
		return effective;
	}
	
	public static TestSet getTestPool(int start, int num) {
		
		TestSet testpool = new TestSet();
		for (int i = start; i < (num + start); i++) {
			testpool.add("" + i);
		}
		return testpool;
	}

	//2009-02-16:load the test pool from the file
	public static TestSet getTestPool(String file, boolean containHeader){
		TestSet testpool = new TestSet();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str = null;
			
			if(containHeader){ //if the first row is the header 
				br.readLine();
			}
			
			while((str = br.readLine())!= null){
				TestCase tc = new TestCase(str);
				testpool.add(tc.index);
				testCases.put(tc.index, tc);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return testpool;
	}
	
	/**
	 * 
	 * @param testCaseFile
	 */
	public static void loadTestCase(String testCaseFile){
		try {
			BufferedReader br = new BufferedReader(new FileReader(testCaseFile));
			String str = br.readLine(); //the first line is the header
			
			while((str = br.readLine())!=null){
				TestCase tc = new TestCase(str);
				Adequacy.testCases.put(tc.index, tc);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void attachTSWithCI(TestSet[] testSets, String saveFile){
		StringBuilder sb = new StringBuilder();
		sb.append("TestSet" + "\t" + "Size" + "\t" + "Coverage" + "\t" + "CI" + "\n");

		for(int j = 0; j < testSets.length; j ++){
			TestSet ts = testSets[j];
			double CI = Adequacy.getAverageCI(ts);
			sb.append(ts.index + "\t" + ts.size() + "\t" + ts.coverage + "\t" + CI + "\n");
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile));
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**2009-2-16:attach test sets with the average Context Intensity of its test cases
	 * 
	 * @param ts
	 * @param testcaseFile
	 * @return
	 */
	public static double getAverageCI(TestSet ts){
		double avg_CI = 0.0;
		double sum_CI = 0.0;
		for(int i = 0; i < ts.testcases.size(); i ++){
			String index_testcase = (String)ts.testcases.get(i);
			sum_CI += ((TestCase)Adequacy.testCases.get(index_testcase)).CI;
		}
		avg_CI = sum_CI/(double)ts.testcases.size();
		return avg_CI;
	}
	
	public static void main(String argv[]) {
	//	testSetSize, instruction, testPoolStartLabel, testPoolSize
//		argv = new String[]{"50", "together_noOrdinary"};
		//2009-1-5
//		argv = new String[]{"1", "Context_Intensity","-100","2"};
		
//		argv = new String[]{"1", "Context_Intensity"};
		
//		CFG g = new CFG("src/ccr/app/TestCFG2.java");
		//2009-2-14: run in the server, no Eclipse supports.
		CFG g = new CFG(System.getProperty("user.dir")+"/src/ccr/app/TestCFG2.java");

		//g.getAllFullResolvedDU();
		Criterion c;
		int testSetsSize = Integer.parseInt(argv[0]);
		int testPoolStartLabel = TestDriver.TEST_POOL_START_LABEL;
		int testPoolSize = TestDriver.TEST_POOL_SIZE;
		String instruction = argv[1];
		if (argv.length == 4) {
			testPoolStartLabel = Integer.parseInt(argv[2]);
			testPoolSize = Integer.parseInt(argv[3]);
		}
		
//		TestSet testpool = getTestPool(testPoolStartLabel, testPoolSize);
		//2009-02-16:test pool is loaded from file
		String testPoolFile = "src/ccr/experiment/Context-Intensity_backup/TestHarness/TestPool_20090216.txt";
		TestSet testpool = getTestPool(testPoolFile, true);
		
		int maxTrials = 2000;
		int[] nums = new int[]{21};
		for(int i = 0; i < nums.length; i ++){			
			int testSuiteSize = nums[i];
			if (instruction.equals("step1")) {
				c = g.getAllUses();		
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/allUsesTestSets.txt");
			} else if (instruction.equals("step2")) {
				c = g.getAllPolicies();
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/allPoliciesTestSets.txt");
			} else if (instruction.equals("step3")) {
				c = g.getAllKResolvedDU(1);
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/all1ResolvedDUTestSets.txt");
			} else if (instruction.equals("step4")) {
				c = g.getAllKResolvedDU(2);
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/all2ResolvedDUTestSets.txt");
			} else if (instruction.equals("step5")) {
				c = g.getAllFullResolvedDU();
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/allFullResolvedDUTestSets.txt");
			} else if (instruction.equals("together")) {
				c = g.getAllUses();
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/allUsesTestSets.txt");
				c = g.getAllPolicies();
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/allPoliciesTestSets.txt");
				c = g.getAllKResolvedDU(1);
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/all1ResolvedDUTestSets.txt");
				c = g.getAllKResolvedDU(2);
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/all2ResolvedDUTestSets.txt");
				c = g.getAllFullResolvedDU();
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/allFullResolvedDUTestSets.txt");
			}else if(instruction.equals("together_noOrdinary")){
				//2008/8/19:version2
//				Criterion allDU = g.getDUAssociations();
//				c = g.getAllPUse(allDU);
//				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
//						"src/ccr/experiment/AllUses_NoOrdinary/allPUsesTestSets_" + testSuiteSize+ ".txt", testSuiteSize);
//				c = g.getAllCUse(allDU);
//				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
//						"src/ccr/experiment/AllUses_NoOrdinary/allCUsesTestSets_" + testSuiteSize+ ".txt", testSuiteSize);
//				c = allDU.normalize(g.getEdgeSet());				
//				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
//						"src/ccr/experiment/AllUses_NoOrdinary/allUsesTestSets_" + testSuiteSize+ ".txt", testSuiteSize);
//				c = g.getAllUse_noOrdinary(c);
//				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
//						"src/ccr/experiment/AllUses_NoOrdinary/allUsesTestSets_noOrdinary_" + testSuiteSize+ ".txt", testSuiteSize);
//																				
//			
//				TestSet testSets[][] = new TestSet[1][];
//				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/AllUses_NoOrdinary/allPUsesTestSets_" + testSuiteSize+ ".txt");
//				String versionPackageName = "testversion";
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/AllUses_NoOrdinary/test-report-allPUses_" + testSuiteSize + ".txt");
//				
//				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/AllUses_NoOrdinary/allCUsesTestSets_" + testSuiteSize+ ".txt");
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/AllUses_NoOrdinary/test-report-allCUses_" + testSuiteSize + ".txt");
//				
//				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/AllUses_NoOrdinary/allUsesTestSets_" + testSuiteSize+ ".txt");
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/AllUses_NoOrdinary/test-report-allUses_" + testSuiteSize + ".txt");
//				
//				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/AllUses_NoOrdinary/allUsesTestSets_noOrdinary_" + testSuiteSize+ ".txt");
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/AllUses_NoOrdinary/test-report-allUses_NoOrdinary_" + testSuiteSize + ".txt");							
//				
				
				//2008/8/21:version 4
				Criterion allDU = g.getDUAssociations();
				Criterion allPUse = g.getAllPUse(allDU);
				getTestSets("TestCFG2_ins", allPUse, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/Cost/100%Coverage+random/allPUsesTestSets_" + testSuiteSize+ ".txt", testSuiteSize);
				Criterion allCUse = g.getAllCUse(allDU);
				getTestSets("TestCFG2_ins", allCUse, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/Cost/100%Coverage+random/allCUsesTestSets_" + testSuiteSize+ ".txt", testSuiteSize);
				allDU = allDU.normalize(g.getEdgeSet());				
				getTestSets("TestCFG2_ins", allDU, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/Cost/100%Coverage+random/allUsesTestSets_" + testSuiteSize+ ".txt", testSuiteSize);
				Criterion allUse_NR = g.getAllUse_noOrdinary(allDU);
				getTestSets("TestCFG2_ins", allUse_NR, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/Cost/100%Coverage+random/allUsesTestSets_noOrdinary_" + testSuiteSize+ ".txt", testSuiteSize);
				Criterion allPUse_NR = g.getAllPUses_noOrdinary(allPUse);
				getTestSets("TestCFG2_ins", allPUse_NR, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/Cost/100%Coverage+random/allPUsesTestSets_noOrdinary_" + testSuiteSize+ ".txt", testSuiteSize);
				Criterion allCUse_NR = g.getAllPUses_noOrdinary(allPUse);
				getTestSets("TestCFG2_ins", allCUse_NR, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/Cost/100%Coverage+random/allCUsesTestSets_noOrdinary_" + testSuiteSize+ ".txt", testSuiteSize);
				
				TestSet testSets[][] = new TestSet[1][];
				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/Cost/100%Coverage+random/allPUsesTestSets_" + testSuiteSize+ ".txt");
				String versionPackageName = "testversion";
				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
						"src/ccr/experiment/Cost/100%Coverage+random/test-report-allPUses_" + testSuiteSize + ".txt");
				
				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/Cost/100%Coverage+random/allCUsesTestSets_" + testSuiteSize+ ".txt");
				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
						"src/ccr/experiment/Cost/100%Coverage+random/test-report-allCUses_" + testSuiteSize + ".txt");
				
				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/Cost/100%Coverage+random/allUsesTestSets_" + testSuiteSize+ ".txt");
				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
						"src/ccr/experiment/Cost/100%Coverage+random/test-report-allUses_" + testSuiteSize + ".txt");
				
				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/Cost/100%Coverage+random/allUsesTestSets_noOrdinary_" + testSuiteSize+ ".txt");
				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
						"src/ccr/experiment/Cost/100%Coverage+random/test-report-allUses_NoOrdinary_" + testSuiteSize + ".txt");
				
				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/Cost/100%Coverage+random/allPUsesTestSets_noOrdinary_" + testSuiteSize+ ".txt");
				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
						"src/ccr/experiment/Cost/100%Coverage+random/test-report-allPUses_NoOrdinary_" + testSuiteSize + ".txt");
				
				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/Cost/100%Coverage+random/allCUsesTestSets_noOrdinary_" + testSuiteSize+ ".txt");
				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
						"src/ccr/experiment/Cost/100%Coverage+random/test-report-allCUses_NoOrdinary_" + testSuiteSize + ".txt");
				
			/*	
				/*
				c = g.getAllPolicies_noOrdinary();
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/allPoliciesTestSets_noOrdinary.txt");
				c = g.getAllKResolvedDU_noOrdinary(1);
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/all1ResolvedDUTestSets_noOrdinary.txt");
				c = g.getAllKResolvedDU_noOrdinary(2);
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/all2ResolvedDUTestSets_noOrdinary.txt");
				c = g.getAllFullResolvedDU_noOrdinary();
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/allFullResolvedDUTestSets_noOrdinary.txt");
						*/
			}else if(instruction.equals("Context_Intensity")){	
				//2009-02-15:generate criterion-adequate test sets
//				c = g.getAllPolicies();
//				int size = Integer.parseInt(argv[2]);
//				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
//						"src/ccr/experiment/Context-Intensity_backup/TestHarness/allPoliciesTestSets_20090216.txt", size);
//				
//				c = g.getAllKResolvedDU(1);
//				size = Integer.parseInt(argv[3]);
//				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
//						"src/ccr/experiment/Context-Intensity_backup/TestHarness/all1ResolvedDUTestSets_20090216.txt",size);
//				
//				c = g.getAllKResolvedDU(2);
//				size = Integer.parseInt(argv[4]);
//				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
//						"src/ccr/experiment/Context-Intensity_backup/TestHarness/all2ResolvedDUTestSets_20090216.txt", size);
//				
//				size = Integer.parseInt(argv[5]);
//				c = g.getAllFullResolvedDU();
//				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
//						"src/ccr/experiment/Context-Intensity_backup/TestHarness/allFullResolvedDUTestSets_20090216.txt", size);
				
				//2009-2-15: execute all test sets to evaluate their fault finding performance
				TestSet testSets[][] = new TestSet[1][];
//				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/allPoliciesTestSets.txt");
				String versionPackageName = "testversion";
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/RQ1/allPolicies/allPolicies.txt");
				
//				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/all1ResolvedDUTestSets.txt");
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/RQ1/all1ResolvedDU/all1ResolvedDU.txt");
				
//				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/all2ResolvedDUTestSets.txt");
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/RQ1/all2ResolvedDU/all2ResolvedDU.txt");
//				
				//2009-2-15:re-shape the output format of testing
//				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/allPoliciesTestSets_20090215.txt");
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/RQ1/allFullResolvedDU/allPolicies_20090215.txt");
//				
//				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/all1ResolvedDUTestSets_20090215.txt");
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/RQ1/allFullResolvedDU/all1ResolvedDU_20090215.txt");
//				
//				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/all2ResolvedDUTestSets_20090215.txt");
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/RQ1/allFullResolvedDU/all2ResolvedDU_20090215.txt");
//				
//				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/allFullResolvedDUTestSets_20090215.txt");
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/RQ1/allFullResolvedDU/allFullResolvedDU_20090215.txt");
				
				//2009-2-15:attach test set with CI information
//				String testCaseFile = "src/ccr/experiment/CI_testcase.txt";
//				Adequacy.loadTestCase(testCaseFile);
//				StringBuilder sb = new StringBuilder();
//				sb.append("TestSet" + "\t" + "Size" + "\t" + "Coverage" + "\t" + "CI" + "\n");
//				
//				String testSetFile = "src/ccr/experiment/all1ResolvedDUTestSets_20090215_CI.txt";
//
//				for(int j = 0; j < testSets[0].length; j ++){
//					TestSet ts = testSets[0][j];
//					double CI = Adequacy.getAverageCI(ts);
//					sb.append(ts.index + "\t" + ts.size() + "\t" + ts.coverage + "\t" + CI + "\n");
//				}
//				
//				try {
//					BufferedWriter bw = new BufferedWriter(new FileWriter(testSetFile));
//					bw.write(sb.toString());
//					bw.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				//2009-02-16:regenerate test sets based on new test pool who have the evenly distributed CI, 
				//and we have no limitations on test set sizes
				c = g.getAllPolicies();
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/Context-Intensity_backup/TestHarness/allPoliciesTestSets_20090216.txt");
				
				c = g.getAllKResolvedDU(1);
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/Context-Intensity_backup/TestHarness/all1ResolvedDUTestSets_20090216.txt");
				
				c = g.getAllKResolvedDU(2);
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/Context-Intensity_backup/TestHarness/all2ResolvedDUTestSets_20090216.txt");
//				
				c = g.getAllFullResolvedDU();
				getTestSets("TestCFG2_ins", c, testpool, maxTrials, testSetsSize, 
						"src/ccr/experiment/Context-Intensity_backup/TestHarness/allFullResolvedDUTestSets_20090215.txt");
				
				//2009-2-16:attach test set with CI information
				Adequacy.loadTestCase(testPoolFile);
				
				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/Context-Intensity_backup/TestHarness/allPoliciesTestSets_20090216.txt");
				Adequacy.attachTSWithCI(testSets[0], "src/ccr/experiment/Context-Intensity_backup/TestHarness/allPoliciesTestSets_20090216_CI.txt");
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/Context-Intensity_backup/TestHarness/allPolicies_20090216.txt");
				
				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/Context-Intensity_backup/TestHarness/all1ResolvedDUTestSets_20090216.txt");
				Adequacy.attachTSWithCI(testSets[0], "src/ccr/experiment/Context-Intensity_backup/TestHarness/all1ResolvedDUTestSets_20090216_CI.txt");
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/Context-Intensity_backup/TestHarness/all1ResolvedDU_20090216.txt");
//				
				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/Context-Intensity_backup/TestHarness/all2ResolvedDUTestSets_20090216.txt");
				Adequacy.attachTSWithCI(testSets[0], "src/ccr/experiment/Context-Intensity_backup/TestHarness/all2ResolvedDUTestSets_20090216_CI.txt");
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/Context-Intensity_backup/TestHarness/all2ResolvedDU_20090216.txt");
				
				testSets[0] = Adequacy.getTestSets("src/ccr/experiment/Context-Intensity_backup/TestHarness/allFullResolvedDUTestSets_20090215.txt");
				Adequacy.attachTSWithCI(testSets[0], "src/ccr/experiment/Context-Intensity_backup/TestHarness/allFullResolvedDUTestSets_20090215_CI.txt");
//				TestDriver.test(versionPackageName, "TestCFG2", testSets, 
//						"src/ccr/experiment/Context-Intensity_backup/TestHarness/allFullResolvedDU_20090215.txt");
				
				
				
				
			}
		}
		
		
		
		
	}

}
