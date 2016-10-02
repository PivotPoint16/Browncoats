package com.bostonburke.cdbgdatamap;

import android.content.res.AssetManager;

import java.util.*;
import java.io.*;

public class CityProfile
{
    private LinkedHashMap<Integer, LinkedHashMap<String, List<Object>>> map;
    private LinkedHashMap<String, List<Double>>                         mp;
    int                                                                 count;
    String                                                              name;
    int                                                                 year;


    /**
     * Constructs the CityProfile class
     *
     * @param years, cityName
     */
    public CityProfile(int years, String cityName) throws FileNotFoundException
    {
        map = new LinkedHashMap<Integer, LinkedHashMap<String, List<Object>>>();
        mp = new LinkedHashMap<String, List<Double>>();
        fillMap(mp);
        fillAllYears();
        count = 0;
        name = cityName;
        year = years;
    }


    /**
     * @return name of city
     */
    public String getName()
    {
        return name;
    }


    public int getYear()
    {
        return year;
    }


    /**
     * @return number of HomeLess People in the city
     */
    public int getNumHomeless(int year)
    {
        return 0;
    }

    public double getAcquisition()
    {
        double funding = 0;

        List<Object> list = map.get(year).get(name);
        for(Object s : list)
        {
            String stuff = s.toString();
            String[] splitted = stuff.split(" ");
            if(splitted[0].equals("Acquisition"))
            {
                funding = Double.parseDouble(splitted[splitted.length-1]);
                break;
            }
        }
        return funding;
    }

    public double getEconomicDev()
    {
        double funding = 0;

        List<Object> list = map.get(year).get(name);
        for(Object s : list)
        {
            String stuff = s.toString();
            String[] splitted = stuff.split(" ");
            if(splitted[0].equals("Economic"))
            {
                funding = Double.parseDouble(splitted[splitted.length-1]);
                break;
            }
        }
        return funding;
    }

    public double getHousing()
    {
        double funding = 0;

        List<Object> list = map.get(year).get(name);
        for(Object s : list)
        {
            String stuff = s.toString();
            String[] splitted = stuff.split(" ");
            if(splitted[0].equals("Housing"))
            {
                funding = Double.parseDouble(splitted[splitted.length-1]);
                break;
            }
        }
        return funding;
    }

    public double getOther()
    {
        double funding = 0;

        List<Object> list = map.get(year).get(name);
        for(Object s : list)
        {
            String stuff = s.toString();
            String[] splitted = stuff.split(" ");
            if(splitted[0].equals("Other"))
            {
                funding = Double.parseDouble(splitted[splitted.length-1]);
                break;
            }
        }
        return funding;
    }

    public double getPublicImprov()
    {
        double funding = 0;

        List<Object> list = map.get(year).get(name);
        for(Object s : list)
        {
            String stuff = s.toString();
            String[] splitted = stuff.split(" ");
            if(splitted[0].equals("Public") && splitted[1].equals("Improvements"))
            {
                funding = Double.parseDouble(splitted[splitted.length-1]);
                break;
            }
        }
        return funding;
    }

    public double getPublicServices()
    {
        double funding = 0;

        List<Object> list = map.get(year).get(name);
        for(Object s : list)
        {
            String stuff = s.toString();
            String[] splitted = stuff.split(" ");
            if(splitted[0].equals("Public") && splitted[1].equals("Services"))
            {
                funding = Double.parseDouble(splitted[splitted.length-1]);
                break;
            }
        }
        return funding;
    }

    /**
     *
     * @return total amount funded to the city within a year
     */
    public double getTotalFunding()
    {
        double funding = 0;

        List<Object> list = map.get(year).get(name);
        for(Object s : list)
        {
            String stuff = s.toString();
            String[] splitted = stuff.split(" ");
            funding = funding + Double.parseDouble(splitted[splitted.length-1]);
        }
        return funding;
    }


    /**
     * @return average amount funded to the city within a year
     */
    public double getAverageFunds(int year)
    {
        List<Object> list = map.get(year).get(name);
        return this.getTotalFunding() / (list.size());
    }


    /**
     * @return X coordinate of the city
     */
    public double getCoorX()
    {
        return mp.get(this.getName()).get(0);
    }


    /**
     * @return Y coordinate of the city
     */
    public double getCoorY()
    {
        return mp.get(this.getName()).get(1);
    }


    private void fillMap(HashMap<String, List<Double>> map)
    {
        String s = "Chicago";
        List<Double> values = new ArrayList<>();
        values.add(41.881832);
        values.add(-87.623177);
        map.put(s, values);

        s = "Los Angeles";
        values = new ArrayList<>();
        values.add(34.052235);
        values.add(-118.243683);
        map.put(s, values);

        s = "San Fransisco";
        values = new ArrayList<>();
        values.add(37.7749);
        values.add(-122.4194);
        map.put(s, values);

        s = "Miami";
        values = new ArrayList<>();
        values.add(25.778135);
        values.add(-80.179100);
        map.put(s, values);

        s = "Boston";
        values = new ArrayList<>();
        values.add(42.3601);
        values.add(-71.0589);
        map.put(s, values);

        s = "Seattle";
        values = new ArrayList<>();
        values.add(47.6062);
        values.add(-122.3321);
        map.put(s, values);

        s = "Washington";
        values = new ArrayList<>();
        values.add(38.9072);
        values.add(-77.0369);
        map.put(s, values);

        s = "New York";
        values = new ArrayList<>();
        values.add(25.778135);
        values.add(-80.179100);
        map.put(s, values);
    }


    public void fillAllYears() throws FileNotFoundException
    {
        map = new LinkedHashMap<Integer, LinkedHashMap<String, List<Object>>>();

        Scanner input = new Scanner(DisplayResults.getSourceFile());
        fillMap(map);
        int key = 0;
        while (input.hasNextLine())
        {
            String gCF = input.nextLine();
            String[] splitted = gCF.split(",");
            if (splitted[0].equals("2009"))
            {
                key = 2009;
                gCF = input.nextLine();
                splitted = gCF.split(",");
            }
            if (splitted[0].equals("2010"))
            {
                key = 2010;
                gCF = input.nextLine();
                splitted = gCF.split(",");
            }
            if (splitted[0].equals("2011"))
            {
                key = 2011;
                gCF = input.nextLine();
                splitted = gCF.split(",");
            }
            if (splitted[0].equals("2012"))
            {
                key = 2012;
                gCF = input.nextLine();
                splitted = gCF.split(",");
            }

            LinkedHashMap<String, List<Object>> city = map.get(key);
            if (city.containsKey(splitted[2].substring(1, splitted[2].length()
                    - 1)))
            {
                List<Object> groupsAndFunds = (List<Object>)city.get(splitted[2]
                        .substring(1, splitted[2].length() - 1));
                String group = splitted[1];
                group = group.substring(1, group.length() - 1);
                group = group + " " + splitted[3];
                groupsAndFunds.add(group);
                city.put(splitted[2].substring(1, splitted[2].length() - 1),
                        groupsAndFunds);
            }
            else
            {
                List<Object> groupsAndFunds = new ArrayList<>();
                String group = splitted[1];
                group = group.substring(1, group.length() - 1);
                group = group + " " + splitted[3];
                groupsAndFunds.add(group);
                city.put(splitted[2].substring(1, splitted[2].length() - 1),
                        groupsAndFunds);
            }
        }
        input.close();
    }


    public static void fillMap(
            LinkedHashMap<Integer, LinkedHashMap<String, List<Object>>> map)
    {
        LinkedHashMap<String, List<Object>> values;
        for (int i = 2009; i < 2013; i++)
        {
            values = new LinkedHashMap<String, List<Object>>();
            map.put(i, values);
        }
    }
}
