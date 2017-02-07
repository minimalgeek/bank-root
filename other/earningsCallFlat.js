db.earnings_call.aggregate( [
	{ $match: 
		{
			publishDate: { "$exists": true, "$ne": null }
		}
	},
	{ $project : 
		{ 
			_id: 1, 
			tradingSymbol : 1, 
			publishDate: 1,
			date_number : {$add :[{$multiply:[{ $year: "$publishDate" }, 10000]}, {$multiply:[{ $month: "$publishDate" }, 100]}, { $dayOfMonth: "$publishDate" }]},
			time_number : {$add :[{$multiply:[{ $hour: "$publishDate" }, 10000]}, {$multiply:[{ $minute: "$publishDate" }, 100]}, { $second: "$publishDate" }]},
			wordSize: { $size: { "$ifNull": [ "$words", [] ] }}, 
			"h_tone.positiveCount": { "$ifNull": [ "$h_tone.positiveCount", -1 ] }, 
			"h_tone.negativeCount": { "$ifNull": [ "$h_tone.negativeCount", -1 ] },
			q_and_a_wordSize: { $size: { "$ifNull": [ "$qAndAWords", [] ] }}, 
			"q_and_a_h_tone.positiveCount": { "$ifNull": [ "$q_and_a_h_tone.positiveCount", -1 ] }, 
			"q_and_a_h_tone.negativeCount": { "$ifNull": [ "$q_and_a_h_tone.negativeCount", -1 ] },
		}
	},
	{ $out: "earnings_call_flat" }
]);