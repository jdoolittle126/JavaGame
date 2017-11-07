uniform sampler2D tex0;

const vec3 fact =vec3 (0.30, 0.59,0.11);

void main(void)
{
	vec4 value = texture2D(tex0, vec2(gl_TexCoord[0]));
	vec4 new;
	float result;
	
	new = value * vec4(fact,1.0);
	
	result = (new.x + new.y + new.z);
	
	gl_FragColor = vec4(result,result,result,1);
}

