# YoMaps

Custom built an XML Parser for real-world open street map data.
It works for Ireland but too slow for demo. Hence using Waterford City Only.

Tests created where reasonable. All Pass.

About 40,000 Nodes &
Thousands of Links

Made a custom BFS - but too many nodes, if radius is large it stack overflows.
Created an Interface for Distance Calc
Used DAs for Quickest and Shortest

Example One for DEMO >>>>

>> Single
High Street to Water Street
Show 1, 2, 3 for Multi

>> Quickest	
Quarry Road to Water Street

Switch to Shortest run again.

>> Add Waypoint: Cleaboy Road
>> Add another Waypoint: High Street

But, now avoid: Catherine Street

And also: Johnstown


> Show ToolTip on Water Street
